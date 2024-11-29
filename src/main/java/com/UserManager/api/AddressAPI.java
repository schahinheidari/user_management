package com.UserManager.api;

import com.UserManager.model.dto.address.AddAddressDto;
import com.UserManager.model.dto.address.UpdateAddressDto;
import com.UserManager.model.dto.address.ViewAddressDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ADDRESS", description = "Address API")
@RequestMapping("/address/v1/")
public interface AddressAPI {

    @Operation(summary = "Save a new address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ViewAddressDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/save")
    ResponseEntity<ViewAddressDto> save(@RequestBody AddAddressDto addAddressDto);

    //**********************************************************************************************

    @Operation(summary = "Update an existing address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ViewAddressDto.class))),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/update/{id}")
    ResponseEntity<ViewAddressDto> update(@PathVariable Long id, @RequestBody UpdateAddressDto updateAddressDto);

    //**********************************************************************************************

    @Operation(summary = "Delete an address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    //**********************************************************************************************

    @Operation(summary = "Find an address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ViewAddressDto.class))),
            @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @GetMapping("/find/{id}")
    ResponseEntity<ViewAddressDto> findById(@PathVariable Long id);

    //**********************************************************************************************

    @Operation(summary = "Get all addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ViewAddressDto.class))))
    })
    @GetMapping("/list")
    ResponseEntity<List<ViewAddressDto>> findAll();
}

