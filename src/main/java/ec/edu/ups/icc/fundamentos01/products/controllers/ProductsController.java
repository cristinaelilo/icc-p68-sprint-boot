package ec.edu.ups.icc.fundamentos01.products.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.icc.fundamentos01.core.dto.PaginationDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.services.ProductService;
import ec.edu.ups.icc.fundamentos01.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Productos",
    description = "Gestión de productos con paginación, roles y control de propietarios."
)
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductService service;

    public ProductsController(ProductService service) {
        this.service = service;
    }

    /*
     * Listar TODOS los productos - SOLO ADMIN (Práctica 12)
     * GET /api/products
     */
    @Operation(
        summary = "Listar todos los productos",
        description = "Obtiene la lista completa de productos. Solo los usuarios con rol ADMIN pueden acceder."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductResponseDto> findAll() {
        return service.findAll();
    }

    /*
     * Crear producto.
     *
     * POST /api/products
     *
     * PRÁCTICA 13: el owner ya no se toma del body,
     * se obtiene del usuario autenticado (token JWT).
     */

    @Operation(
        summary = "Crear producto",
        description = "Crea un nuevo producto y lo asocia al usuario autenticado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto create(
            @Valid @RequestBody CreateProductDto dto,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        return service.create(dto, currentUser);
    }

    @Operation(
        summary = "Buscar producto por ID",
        description = "Obtiene la información de un producto a partir de su identificador."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ProductResponseDto findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    /*
     * Actualizar producto completo.
     *
     * PUT /api/products/{id}
     *
     * PRÁCTICA 13: valida ownership en el servicio.
     * Propietario o ADMIN pueden actualizar.
     */

    @Operation(
        summary = "Actualizar producto",
        description = "Actualiza completamente un producto. Solo el propietario o un ADMIN pueden realizar esta operación."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ProductResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductDto dto,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        return service.update(id, dto, currentUser);
    }

    /*
     * Actualizar producto parcial.
     *
     * PATCH /api/products/{id}
     *
     * PRÁCTICA 13: valida ownership en el servicio.
     */

    @Operation(
        summary = "Actualizar parcialmente un producto",
        description = "Modifica parcialmente la información de un producto."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{id}")
    public ProductResponseDto partialUpdate(
            @PathVariable Long id,
            @RequestBody PartialUpdateProductDto dto,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        return service.partialUpdate(id, dto, currentUser);
    }

    /*
     * Eliminar producto (lógico).
     *
     * DELETE /api/products/{id}
     *
     * PRÁCTICA 13: valida ownership en el servicio.
     */

    @Operation(
        summary = "Eliminar producto",
        description = "Realiza la eliminación lógica de un producto."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado"),
        @ApiResponse(responseCode = "403", description = "No tiene permisos"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        service.delete(id, currentUser);
    }


    @Operation(
        summary = "Listar productos por usuario",
        description = "Obtiene todos los productos pertenecientes a un usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente")
    })
    @GetMapping("/user/{userId}")
    public List<ProductResponseDto> findByUser(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }


    @Operation(
        summary = "Listar productos por categoría",
        description = "Obtiene todos los productos de una categoría."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente")
    })
    @GetMapping("/category/{categoryId}")
    public List<ProductResponseDto> findByCategory(@PathVariable Long categoryId) {
        return service.findByCategoryId(categoryId);
    }

    /*
     * ==========================
     * PRÁCTICA 10
     * ==========================
     */


    @Operation(
        summary = "Listar productos paginados",
        description = "Obtiene una página de productos utilizando paginación."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Página obtenida correctamente")
    })
    @GetMapping("/page")
    public Page<ProductResponseDto> findAllPage(
            @Valid @ModelAttribute PaginationDto pagination
    ) {
        return service.findAllPage(pagination);
    }


    @Operation(
        summary = "Mis productos",
        description = "Obtiene los productos del usuario autenticado utilizando paginación tipo Slice."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente"),
        @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @GetMapping("/slice")
    public Slice<ProductResponseDto> findMyProducts(
            @AuthenticationPrincipal UserDetailsImpl currentUser,
            @Valid @ModelAttribute PaginationDto pagination
    ) {
        return service.findMyProductsSlice(currentUser, pagination);
    }
}


