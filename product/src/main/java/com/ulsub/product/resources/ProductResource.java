package com.ulsub.product.resources;

import com.ulsub.product.ProductService;
import com.ulsub.product.dto.ProductDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Path("/v1")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    // TODO adds validation
    @POST
    @Path("/product")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(ProductDto productDto) {
        Long productId = productService.addProduct(productDto);
        return Response.status(Response.Status.CREATED).entity(productId).build();
    }

    @GET
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDto> findAllProducts() {
        return productService.findAllProducts();
    }

    @GET
    @Path("/product/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto findProduct(@PathParam("id") Long id) {
        return productService.findProduct(id);
    }

    @DELETE
    @Path("/product/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("id") Long id) {
        productService.deleteProduct(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
