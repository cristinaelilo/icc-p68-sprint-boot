package ec.edu.ups.icc.fundamentos01.products.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.ups.icc.fundamentos01.categories.entities.CategoryEntity;
import ec.edu.ups.icc.fundamentos01.categories.repositories.CategoryRepository;
import ec.edu.ups.icc.fundamentos01.core.dto.PaginationDto;
import ec.edu.ups.icc.fundamentos01.core.exceptions.domain.BadRequestException;
import ec.edu.ups.icc.fundamentos01.core.exceptions.domain.ConflictException;
import ec.edu.ups.icc.fundamentos01.core.exceptions.domain.NotFoundException;
import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductFilterByUserDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import ec.edu.ups.icc.fundamentos01.products.respositories.ProductRepository;
import ec.edu.ups.icc.fundamentos01.security.services.UserDetailsImpl;
import ec.edu.ups.icc.fundamentos01.users.entity.UserEntity;
import ec.edu.ups.icc.fundamentos01.users.repository.UserRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;
    private final UserRepository userRepo;
    private final CategoryRepository catRepo;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id", "name", "price", "stock", "createdAt", "updatedAt"
    );

    public ProductServiceImpl(ProductRepository repo,
                              UserRepository userRepo,
                              CategoryRepository catRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.catRepo = catRepo;
    }

    // ==========================
    // CREATE PRODUCT (PRÁCTICA 13: owner desde el token)
    // ==========================
    @Override
    @Transactional
    public ProductResponseDto create(CreateProductDto dto, UserDetailsImpl currentUser) {

        UserEntity owner = findCurrentUserEntity(currentUser);

        validateProductNameForCreate(dto.getName());

        Set<CategoryEntity> categories = findActiveCategories(dto.getCategoryIds());

        ProductEntity p = new ProductEntity();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        p.setOwner(owner);
        p.setCategories(categories);

        return map(repo.save(p));
    }

    // ==========================
    // FIND ALL
    // ==========================
    @Override
    public List<ProductResponseDto> findAll() {
        return repo.findByDeletedFalse()
                .stream()
                .map(this::map)
                .toList();
    }

    // ==========================
    // FIND ONE
    // ==========================
    @Override
    public ProductResponseDto findOne(Long id) {
        return repo.findByIdAndDeletedFalse(id)
                .map(this::map)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    // ==========================
    // DELETE (PRÁCTICA 13: valida ownership)
    // ==========================
    @Override
    @Transactional
    public void delete(Long id, UserDetailsImpl currentUser) {

        ProductEntity p = findActiveProductOrThrow(id);

        validateOwnership(p, currentUser);

        p.setDeleted(true);
        repo.save(p);
    }

    // ==========================
    // FIND BY USER (sin filtros)
    // ==========================
    @Override
    public List<ProductResponseDto> findByUserId(Long userId) {
        return repo.findByOwner_IdAndDeletedFalse(userId)
                .stream()
                .map(this::map)
                .toList();
    }

    // ==========================
    // FIND BY CATEGORY (sin filtros)
    // ==========================
    @Override
    public List<ProductResponseDto> findByCategoryId(Long categoryId) {
        return repo.findByCategoryIdAndDeletedFalse(categoryId)
                .stream()
                .map(this::map)
                .toList();
    }

    // ==========================
    // UPDATE PUT (PRÁCTICA 13: valida ownership)
    // ==========================
    @Override
    @Transactional
    public ProductResponseDto update(Long id, UpdateProductDto dto, UserDetailsImpl currentUser) {

        ProductEntity p = findActiveProductOrThrow(id);

        validateOwnership(p, currentUser);

        validateProductNameForUpdate(p.getId(), dto.getName());

        Set<CategoryEntity> categories = findActiveCategories(dto.getCategoryIds());

        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        p.setCategories(categories);

        return map(repo.save(p));
    }

    // ==========================
    // PATCH (PRÁCTICA 13: valida ownership)
    // ==========================
    @Override
    @Transactional
    public ProductResponseDto partialUpdate(Long id, PartialUpdateProductDto dto, UserDetailsImpl currentUser) {

        ProductEntity p = findActiveProductOrThrow(id);

        validateOwnership(p, currentUser);

        if (dto.getName() != null) {
            validateProductNameForUpdate(p.getId(), dto.getName());
            p.setName(dto.getName());
        }
        if (dto.getPrice() != null) p.setPrice(dto.getPrice());
        if (dto.getStock() != null) p.setStock(dto.getStock());

        if (dto.getCategoryIds() != null) {
            Set<CategoryEntity> categories = findActiveCategories(dto.getCategoryIds());
            p.setCategories(categories);
        }

        return map(repo.save(p));
    }

    // ==========================
    // PRACTICA 9: FILTER BY USER
    // ==========================
    @Override
    public List<ProductResponseDto> findByUserIdWithFilters(
            Long userId,
            ProductFilterByUserDto filters
    ) {
        if (!userRepo.existsByIdAndDeletedFalse(userId)) {
            throw new NotFoundException("User not found");
        }

        validateFilters(filters);

        String name = normalizeName(filters.getName());

        return repo.findByOwnerIdWithFilters(
                        userId, name, filters.getMinPrice(), filters.getMaxPrice(), filters.getCategoryId()
                )
                .stream()
                .map(this::map)
                .toList();
    }

    // ==========================
    // PRACTICA 9: FILTER BY CATEGORY
    // ==========================
    @Override
    public List<ProductResponseDto> findByCategoryIdWithFilters(
            Long categoryId,
            ProductFilterByUserDto filters
    ) {
        if (!catRepo.existsByIdAndDeletedFalse(categoryId)) {
            throw new NotFoundException("Category not found");
        }

        validateFilters(filters);

        String name = normalizeName(filters.getName());

        return repo.findByCategoryIdWithFilters(
                        categoryId, name, filters.getMinPrice(), filters.getMaxPrice(), filters.getUserId()
                )
                .stream()
                .map(this::map)
                .toList();
    }

    // ==========================
    // PRACTICA 10: FIND ALL PAGE / SLICE
    // ==========================
    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> findAllPage(PaginationDto pagination) {
        Pageable pageable = createPageable(pagination);
        return repo.findActivePage(pageable).map(this::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<ProductResponseDto> findMyProductsSlice(
            UserDetailsImpl currentUser,
            PaginationDto pagination
    ) {

        Pageable pageable = createPageable(pagination);

        return repo.findByOwnerIdAndDeletedFalse(
                currentUser.getId(),
                pageable
        ).map(this::map);
    }

    // ==========================
    // PRACTICA 10: CATEGORY PAGE/SLICE CON FILTROS
    // ==========================
    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> findByCategoryIdWithFiltersPage(
            Long categoryId,
            ProductFilterByUserDto filters,
            PaginationDto pagination
    ) {
        if (!catRepo.existsByIdAndDeletedFalse(categoryId)) {
            throw new NotFoundException("Category not found");
        }

        validateFilters(filters);

        String name = normalizeName(filters.getName());

        Pageable pageable = createPageable(pagination);

        return repo.findByCategoryIdWithFiltersPage(
                        categoryId, name, filters.getMinPrice(), filters.getMaxPrice(), filters.getUserId(), pageable
                )
                .map(this::map);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<ProductResponseDto> findByCategoryIdWithFiltersSlice(
            Long categoryId,
            ProductFilterByUserDto filters,
            PaginationDto pagination
    ) {
        if (!catRepo.existsByIdAndDeletedFalse(categoryId)) {
            throw new NotFoundException("Category not found");
        }

        validateFilters(filters);

        String name = normalizeName(filters.getName());

        Pageable pageable = createPageable(pagination);

        return repo.findByCategoryIdWithFiltersSlice(
                        categoryId, name, filters.getMinPrice(), filters.getMaxPrice(), filters.getUserId(), pageable
                )
                .map(this::map);
    }

    // ==========================
    // HELPER: PRODUCTO ACTIVO O 404
    // ==========================
    private ProductEntity findActiveProductOrThrow(Long id) {
        return repo.findById(id)
                .filter(product -> !product.isDeleted())
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    // ==========================
    // HELPER: USUARIO AUTENTICADO COMO ENTIDAD JPA
    // ==========================
    private UserEntity findCurrentUserEntity(UserDetailsImpl currentUser) {

        if (currentUser == null) {
            throw new AccessDeniedException("Usuario no autenticado");
        }

        return userRepo.findByIdAndDeletedFalse(currentUser.getId())
                .orElseThrow(() -> new AccessDeniedException("Usuario no autorizado"));
    }

    // ==========================
    // HELPER: VALIDAR OWNERSHIP
    // ==========================
    private void validateOwnership(ProductEntity product, UserDetailsImpl currentUser) {

        if (currentUser == null) {
            throw new AccessDeniedException("Usuario no autenticado");
        }

        // ADMIN puede modificar cualquier producto
        if (hasRole(currentUser, "ROLE_ADMIN")) {
            return;
        }

        if (product.getOwner() == null || product.getOwner().getId() == null) {
            throw new AccessDeniedException("El producto no tiene propietario válido");
        }

        if (!product.getOwner().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("No puedes modificar productos ajenos");
        }
    }

    // ==========================
    // HELPER: VALIDAR ROL
    // ==========================
    private boolean hasRole(UserDetailsImpl user, String role) {
        return user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals(role));
    }

    // ==========================
    // HELPER: NOMBRE ÚNICO AL CREAR
    // ==========================
    private void validateProductNameForCreate(String name) {
        if (repo.findByNameIgnoreCaseAndDeletedFalse(name.trim()).isPresent()) {
            throw new ConflictException("Product name already registered");
        }
    }

    // ==========================
    // HELPER: NOMBRE ÚNICO AL ACTUALIZAR
    // ==========================
    private void validateProductNameForUpdate(Long currentProductId, String name) {
        repo.findByNameIgnoreCaseAndDeletedFalse(name.trim())
                .filter(product -> !product.getId().equals(currentProductId))
                .ifPresent(product -> {
                    throw new ConflictException("Product name already registered");
                });
    }

    // ==========================
    // HELPER: CATEGORÍAS ACTIVAS
    // ==========================
    private Set<CategoryEntity> findActiveCategories(Set<Long> categoryIds) {

        if (categoryIds == null || categoryIds.isEmpty()) {
            throw new BadRequestException("Debe seleccionar al menos una categoría");
        }

        Set<Long> uniqueIds = new HashSet<>(categoryIds);

        Set<CategoryEntity> categories = catRepo.findAllById(uniqueIds)
                .stream()
                .filter(category -> !category.isDeleted())
                .collect(Collectors.toSet());

        if (categories.size() != uniqueIds.size()) {
            throw new NotFoundException("One or more categories were not found");
        }

        return categories;
    }

    // ==========================
    // HELPER: CONSTRUIR PAGEABLE
    // ==========================
    private Pageable createPageable(PaginationDto pagination) {
        String sortBy = normalizeSortBy(pagination.getSortBy());
        Sort.Direction direction = normalizeDirection(pagination.getDirection());
        Sort sort = Sort.by(direction, sortBy);
        return PageRequest.of(pagination.getPage(), pagination.getSize(), sort);
    }

    private String normalizeSortBy(String sortBy) {
        if (sortBy == null || sortBy.isBlank()) return "id";
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new BadRequestException("Campo de ordenamiento no permitido: " + sortBy);
        }
        return sortBy;
    }

    private Sort.Direction normalizeDirection(String direction) {
        if (direction == null || direction.isBlank()) return Sort.Direction.ASC;
        if (direction.equalsIgnoreCase("asc")) return Sort.Direction.ASC;
        if (direction.equalsIgnoreCase("desc")) return Sort.Direction.DESC;
        throw new BadRequestException("Dirección de ordenamiento no válida: " + direction);
    }

    // ==========================
    // VALIDATE FILTERS
    // ==========================
    private void validateFilters(ProductFilterByUserDto filters) {

        if (filters == null) return;

        if (!filters.hasValidPriceRange()) {
            throw new BadRequestException("El precio máximo debe ser mayor o igual al mínimo");
        }

        if (filters.getCategoryId() != null &&
                !catRepo.existsByIdAndDeletedFalse(filters.getCategoryId())) {
            throw new NotFoundException("Category not found");
        }

        if (filters.getUserId() != null &&
                !userRepo.existsByIdAndDeletedFalse(filters.getUserId())) {
            throw new NotFoundException("User not found");
        }
    }

    // ==========================
    // NORMALIZE NAME
    // ==========================
    private String normalizeName(String name) {
        if (name == null || name.isBlank()) return null;
        return name.trim();
    }

    // ==========================
    // ENTITY -> DTO
    // ==========================
    private ProductResponseDto map(ProductEntity p) {

        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setStock(p.getStock());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());

        if (p.getOwner() != null) {
            ProductResponseDto.UserDto u = new ProductResponseDto.UserDto();
            u.setId(p.getOwner().getId());
            u.setName(p.getOwner().getName());
            u.setEmail(p.getOwner().getEmail());
            dto.setOwner(u);
        }

        if (p.getCategories() != null) {
            List<ProductResponseDto.CategoryDto> categories = p.getCategories()
                    .stream()
                    .map(cat -> {
                        ProductResponseDto.CategoryDto c = new ProductResponseDto.CategoryDto();
                        c.setId(cat.getId());
                        c.setName(cat.getName());
                        c.setDescription(cat.getDescription());
                        return c;
                    })
                    .toList();
            dto.setCategories(categories);
        }

        return dto;
    }
}
