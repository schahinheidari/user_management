package com.UserManager.api;

import com.UserManager.model.entites.RolePermission;
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


@Tag(name = "ROLE_PERMISSION", description = "RolePermission API")
@RequestMapping("/RolePermission/v1/")
public interface RolePermissionAPI {

    @Operation(summary = "Save a new RolePermission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "RolePermission created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolePermission.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    RolePermission save(@RequestBody RolePermission rolePermission);

    //**********************************************************************************************

    @Operation(summary = "Update an existing RolePermission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "RolePermission updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolePermission.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "RolePermission not found")
    })
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    RolePermission update(@RequestBody RolePermission rolePermission);

    //**********************************************************************************************

    @Operation(summary = "Delete a RolePermission by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "RolePermission deleted successfully"),
            @ApiResponse(responseCode = "404", description = "RolePermission not found")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);

    //**********************************************************************************************

    @Operation(summary = "Find a RolePermission by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "RolePermission found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolePermission.class))),
            @ApiResponse(responseCode = "404", description = "RolePermission not found")
    })
    @GetMapping("/find/{id}")
    RolePermission findById(@PathVariable Long id);

    //**********************************************************************************************

    @Operation(summary = "Get all RolePermissions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "RolePermissions retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RolePermission.class))))
    })
    @GetMapping("/list")
    List<RolePermission> findAll();
}

