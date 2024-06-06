package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Double-Hong
 * @since 2024-05-02 19:39:33
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {

    //所有设备的mapper

    @Resource
    AwningLightMapper awningLightMapper;

    @Resource
    CameraMapper cameraMapper;

    @Resource
    CarDetectorMapper carDetectorMapper;

    @Resource
    EntranceEquipmentMapper entranceEquipmentMapper;

    @Resource
    EtcAntennaMapper etcAntennaMapper;

    @Resource
    ExportPaymentEquipmentMapper exportPaymentEquipmentMapper;

    @Resource
    InductionScreenMapper inductionScreenMapper;

    @Resource
    IntelBoardMapper intelBoardMapper;

    @Resource
    LaneWeighingEquipmentMapper laneWeighingEquipmentMapper;

    @Resource
    AwningLightLogMapper awningLightLogMapper;
    @Resource
    CameraLogMapper cameraLogMapper;
    @Resource
    CarDetectorLogMapper carDetectorLogMapper;
    @Resource
    EntranceEquipmentLogMapper entranceEquipmentLogMapper;
    @Resource
    EtcAntennaLogMapper etcAntennaLogMapper;
    @Resource
    ExportPaymentEquipmentLogMapper exportPaymentEquipmentLogMapper;
    @Resource
    InductionScreenLogMapper inductionScreenLogMapper;
    @Resource
    IntelBoardLogMapper intelBoardLogMapper;
    @Resource
    LaneWeighingEquipmentLogMapper laneWeighingEquipmentLogMapper;

    public void backupAllDeviceLogs() {
        backupAwningLightLogs();
        backupCameraLogs();
        backupCarDetectorLogs();
        backupEntranceEquipmentLogs();
        backupEtcAntennaLogs();
        backupExportPaymentEquipmentLogs();
        backupInductionScreenLogs();
        backupIntelBoardLogs();
        backupLaneWeighingEquipmentLogs();
    }

    private void backupAwningLightLogs() {
        List<AwningLightEntity> awningLights = awningLightMapper.selectList(null);
        for (AwningLightEntity awningLight : awningLights) {
            AwningLightLogEntity log = new AwningLightLogEntity();
            log.setAwningLightId(awningLight.getAwningLightId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(awningLight.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setBrightness(awningLight.getBrightness());
            log.setEquipmentIp(awningLight.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(awningLight.getAwningLightName());
            log.setWriterName("System");

            awningLightLogMapper.insert(log);
        }
    }

    private void backupCameraLogs() {
        List<CameraEntity> cameras = cameraMapper.selectList(null);
        for (CameraEntity camera : cameras) {
            CameraLogEntity log = new CameraLogEntity();
            log.setCameraId(camera.getCameraId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(camera.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setFocalLength(camera.getFocalLength());
            log.setAperture(camera.getAperture());
            log.setEquipmentIp(camera.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(camera.getCameraName());
            log.setWriterName("System");

            cameraLogMapper.insert(log);
        }
    }

    private void backupCarDetectorLogs() {
        List<CarDetectorEntity> carDetectors = carDetectorMapper.selectList(null);
        for (CarDetectorEntity carDetector : carDetectors) {
            CarDetectorLogEntity log = new CarDetectorLogEntity();
            log.setCarDetectorId(carDetector.getCarDetectorId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(carDetector.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setDetectionMethod(carDetector.getDetectionMethod());
            log.setDetectionRange(carDetector.getDetectionRange());
            log.setEquipmentIp(carDetector.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(carDetector.getCarDetectorName());
            log.setWriterName("System");

            carDetectorLogMapper.insert(log);
        }
    }

    private void backupEntranceEquipmentLogs() {
        List<EntranceEquipmentEntity> entranceEquipments = entranceEquipmentMapper.selectList(null);
        for (EntranceEquipmentEntity entranceEquipment : entranceEquipments) {
            EntranceEquipmentLogEntity log = new EntranceEquipmentLogEntity();
            log.setEntranceEquipmentId(entranceEquipment.getEntranceEquipmentId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(entranceEquipment.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setCardNumber(entranceEquipment.getCardNumber());
            log.setEquipmentIp(entranceEquipment.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(entranceEquipment.getEntranceName());
            log.setWriterName("System");

            entranceEquipmentLogMapper.insert(log);
        }
    }

    private void backupEtcAntennaLogs() {
        List<EtcAntennaEntity> etcAntennas = etcAntennaMapper.selectList(null);
        for (EtcAntennaEntity etcAntenna : etcAntennas) {
            EtcAntennaLogEntity log = new EtcAntennaLogEntity();
            log.setAntennaId(etcAntenna.getAntennaId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(etcAntenna.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setFrequency(etcAntenna.getFrequency());
            log.setReadRange(etcAntenna.getReadRange());
            log.setBeamWidth(etcAntenna.getBeamWidth());
            log.setEquipmentIp(etcAntenna.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(etcAntenna.getAntennaName());
            log.setWriterName("System");

            etcAntennaLogMapper.insert(log);
        }
    }

    private void backupExportPaymentEquipmentLogs() {
        List<ExportPaymentEquipmentEntity> exportPaymentEquipments = exportPaymentEquipmentMapper.selectList(null);
        for (ExportPaymentEquipmentEntity exportPaymentEquipment : exportPaymentEquipments) {
            ExportPaymentEquipmentLogEntity log = new ExportPaymentEquipmentLogEntity();
            log.setExportEquipmentId(exportPaymentEquipment.getExportEquipmentId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(exportPaymentEquipment.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setReceiptNumber(exportPaymentEquipment.getReceiptNumber());
            log.setScannerState(exportPaymentEquipment.getScannerState());
            log.setEquipmentIp(exportPaymentEquipment.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(exportPaymentEquipment.getExportName());
            log.setWriterName("System");

            exportPaymentEquipmentLogMapper.insert(log);
        }
    }

    private void backupInductionScreenLogs() {
        List<InductionScreenEntity> inductionScreens = inductionScreenMapper.selectList(null);
        for (InductionScreenEntity inductionScreen : inductionScreens) {
            InductionScreenLogEntity log = new InductionScreenLogEntity();
            log.setInductionScreenId(inductionScreen.getInductionScreenId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(inductionScreen.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setBrightness(inductionScreen.getBrightness());
            log.setContrastRatio(inductionScreen.getContrastRatio());
            log.setDisplayRate(inductionScreen.getDisplayRate());
            log.setEquipmentIp(inductionScreen.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(inductionScreen.getInductionScreenName());
            log.setWriterName("System");

            inductionScreenLogMapper.insert(log);
        }
    }

    private void backupIntelBoardLogs() {
        List<IntelBoardEntity> intelBoards = intelBoardMapper.selectList(null);
        for (IntelBoardEntity intelBoard : intelBoards) {
            IntelBoardLogEntity log = new IntelBoardLogEntity();
            log.setLedBoardId(intelBoard.getLedBoardId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(intelBoard.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setDisplayRate(intelBoard.getDisplayRate());
            log.setBrightness(intelBoard.getBrightness());
            log.setContrastRatio(intelBoard.getContrastRatio());
            log.setEquipmentIp(intelBoard.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(intelBoard.getLedBoardName());
            log.setWriterName("System");

            intelBoardLogMapper.insert(log);
        }
    }

    private void backupLaneWeighingEquipmentLogs() {
        List<LaneWeighingEquipmentEntity> laneWeighingEquipments = laneWeighingEquipmentMapper.selectList(null);
        for (LaneWeighingEquipmentEntity laneWeighingEquipment : laneWeighingEquipments) {
            LaneWeighingEquipmentLogEntity log = new LaneWeighingEquipmentLogEntity();
            log.setLaneWeighingId(laneWeighingEquipment.getLaneWeighingId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setState(laneWeighingEquipment.getState());
            log.setErrorCode(""); // 修改为 ""
            log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
            log.setWeighingMachineState(laneWeighingEquipment.getWeighingMachineState());
            log.setDisplayState(laneWeighingEquipment.getDisplayState());
            log.setEquipmentIp(laneWeighingEquipment.getEquipmentIp());
            log.setLogType("常规日志"); // 修改为 "常规日志"
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(laneWeighingEquipment.getLaneWeighingName());
            log.setWriterName("System");

            laneWeighingEquipmentLogMapper.insert(log);
        }
    }
}
