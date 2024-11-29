package com.UserManager.api;

import com.UserManager.model.entites.Permission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PERMISSION", description = "Permission API")
@RequestMapping("/permission/v1")
public interface PermissionAPI {

    @Operation(summary = "Save a new Permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permission created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Permission.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    Permission save(@RequestBody Permission permission);

    //**********************************************************************************************

    @Operation(summary = "Update an existing permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Permission.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    Permission update(@RequestBody Permission permission);

    //**********************************************************************************************

    @Operation(summary = "Delete a permission by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Permission deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);

    //**********************************************************************************************

    @Operation(summary = "Find a Permission by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Permission.class))),
            @ApiResponse(responseCode = "404", description = "Permission not found")
    })
    @GetMapping("/find/{id}")
    Permission findById(@PathVariable Long id);

    //**********************************************************************************************

    @Operation(summary = "Get all permissions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissions retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Permission.class))))
    })
    @GetMapping("/list")
    List<Permission> findAll();
}
