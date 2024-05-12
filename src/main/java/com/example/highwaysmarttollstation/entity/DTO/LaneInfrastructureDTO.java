package com.example.highwaysmarttollstation.entity.DTO;

import com.example.highwaysmarttollstation.entity.*;

import java.util.List;

/**
 * @author :Double-Hong
 * @name :HighwaySmartTollStation
 * @date :2024/5/11
 * @time :下午2:21
 **/
public class LaneInfrastructureDTO {

    public List<CameraEntity> cameraEntities;

    public AwningLightEntity awningLightEntity;

    public CarDetectorEntity carDetectorEntity;

    public IntelBoardEntity intelBoardEntity;

    public LaneWeighingEquipmentEntity laneWeighingEquipmentEntity;
}
