package com.velocity.contract.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;


public class ValidationBean {
    @NotNull(message = "null")
    private Integer id;

    @Valid // CascadingMetaDataBuilder#isCascading
    @NotNull(message = "哈哈哈")
    private ValidationBeanItem item;

    @Override
    public String toString() {
        return "ValidationBean{" +
                "id=" + id +
                ", item=" + item +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ValidationBeanItem getItem() {
        return item;
    }

    public void setItem(ValidationBeanItem item) {
        this.item = item;
    }
}
