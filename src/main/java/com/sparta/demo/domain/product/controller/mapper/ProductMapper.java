package com.sparta.demo.domain.product.controller.mapper;

import com.sparta.demo.domain.product.dto.ProductRequestDto;
import com.sparta.demo.domain.product.service.dto.ProductServiceInputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // ProductRequestDto -> ProductServiceInputDto
//    ProductServiceInputDto toService(ProductRequestDto request);
}
