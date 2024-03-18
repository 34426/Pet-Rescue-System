package com.rabbiter.pet.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-04-02
 */
@ApiModel(value = "Sterilization对象", description = "")
public class Sterilization implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("流浪动物id")
    private Integer animalId;

    @ApiModelProperty("捕捉状态")
    private String catch1;
    private String animalName;

    @ApiModelProperty("绝育状态")
    private String sterilization;

    @ApiModelProperty("放生状态")
    private String release1;

    @ApiModelProperty("疫苗状态")
    private String vaccine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public String getCatch1() {
        return catch1;
    }

    public void setCatch1(String catch1) {
        this.catch1 = catch1;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getSterilization() {
        return sterilization;
    }

    public void setSterilization(String sterilization) {
        this.sterilization = sterilization;
    }

    public String getRelease1() {
        return release1;
    }

    public void setRelease1(String release1) {
        this.release1 = release1;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }
}
