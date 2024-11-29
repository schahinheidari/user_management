package com.UserManager.api;

import com.UserManager.model.entites.Role;
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

@Tag(name = "ROLE", description = "Role API")
@RequestMapping("/role/v1")
public interface RoleAPI {

    @Operation(summary = "Save a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    Role save(@RequestBody Role role);

    //**********************************************************************************************

    @Operation(summary = "Update an existing role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    Role update(@RequestBody Role role);

    //**********************************************************************************************

    @Operation(summary = "Delete a role by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);

    //**********************************************************************************************

    @Operation(summary = "Find a role by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/find/{id}")
    Role findById(@PathVariable Long id);

    //**********************************************************************************************

    @Operation(summary = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Role.class))))
    })
    @GetMapping("/list")
    List<Role> findAll();
}

