package com.example.highwaysmarttollstation.entity.DTO;

import com.example.highwaysmarttollstation.entity.CameraEntity;
import com.example.highwaysmarttollstation.entity.EtcAntennaEntity;
import com.example.highwaysmarttollstation.entity.InductionScreenEntity;

import java.util.List;

/**
 * @author :Double-Hong
 * @name :HighwaySmartTollStation
 * @date :2024/5/4
 * @time :11:29
 **/
public class PreTransactionDTO {
    public List<CameraEntity> cameraEntities;

    public EtcAntennaEntity etcAntennaEntity;

    public InductionScreenEntity inductionScreenEntity;
}
