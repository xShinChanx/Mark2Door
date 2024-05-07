package com.fontys.userservice.model.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateShopRequest {
    private String name;
    private String description;
    private Long ownerId;
}
