package com.UserManager.api;

import com.UserManager.model.entites.UserRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Tag(name = "USER_ROLE", description = "UserRole API")
@RequestMapping("UserRole/v1/")
public interface UserRoleAPI {

    @Operation(summary = "Save a new UserRole")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "UserRole created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRole.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    UserRole save(@RequestBody UserRole userRole);

    @Operation(summary = "Update an existing UserRole")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserRole updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRole.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "UserRole not found")
    })
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    UserRole update(@RequestBody UserRole userRole);

    @Operation(summary = "Delete a UserRole by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "UserRole deleted successfully"),
            @ApiResponse(responseCode = "404", description = "UserRole not found")
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);

    @Operation(summary = "Find a UserRole by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserRole found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRole.class))),
            @ApiResponse(responseCode = "404", description = "UserRole not found")
    })
    @GetMapping("/find/{id}")
    UserRole findById(@PathVariable Long id);

    @Operation(summary = "Get all UserRoles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserRoles retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserRole.class))))
    })
    @GetMapping("/list")
    List<UserRole> findAll();
}

