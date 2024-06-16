package com.example.highwaysmarttollstation.service.impl;

import com.example.highwaysmarttollstation.entity.*;
import com.example.highwaysmarttollstation.mapper.*;
import com.example.highwaysmarttollstation.service.*;
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

    @Resource
    AwningLightService awningLightService;

    @Resource
    CameraService cameraService;

    @Resource
    CarDetectorService carDetectorService;

    @Resource
    EntranceEquipmentService entranceEquipmentService;

    @Resource
    EtcAntennaService etcAntennaService;

    @Resource
    ExportPaymentEquipmentService exportPaymentEquipmentService;

    @Resource
    InductionScreenService inductionScreenService;

    @Resource
    IntelBoardService intelBoardService;

    @Resource
    LaneWeighingEquipmentService laneWeighingEquipmentService;

    @Resource
    DeviceThresholdsMapper deviceThresholdsMapper;

    DeviceThresholdsEntity deviceThresholdsEntity;

    public void backupAllDeviceLogs() {
        deviceThresholdsEntity = deviceThresholdsMapper.selectById("001");
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
            log.setErrorCode(""); // 修改为 ""
            log.setBrightness(awningLight.getBrightness());
            log.setFixtureType(awningLight.getFixtureType());
            log.setEquipmentIp(awningLight.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(awningLight.getAwningLightName());
            log.setWriterName("System");

            if (awningLight.getBrightness() < deviceThresholdsEntity.getAwningBrightness()) {
                log.setState("未连接");
                log.setDescription("亮度异常"); // 修改为 "亮度异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                awningLightService.setEquipmentState(awningLight.getAwningLightId(), "未连接");
            } else {
                log.setState(awningLight.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            awningLightLogMapper.insert(log);
        }
    }

    // 备份摄像头日志
    private void backupCameraLogs() {
        List<CameraEntity> cameras = cameraMapper.selectList(null);
        for (CameraEntity camera : cameras) {
            CameraLogEntity log = new CameraLogEntity();
            log.setCameraId(camera.getCameraId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setErrorCode(""); // 修改为 ""
            log.setFocalLength(camera.getFocalLength());
            log.setAperture(camera.getAperture());
            log.setEquipmentIp(camera.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(camera.getCameraName());
            log.setWriterName("System");

            if (camera.getFocalLength() < deviceThresholdsEntity.getFocalLength() || Float.parseFloat(camera.getAperture()) < deviceThresholdsEntity.getAperture()) {
                log.setState("未连接");
                log.setDescription("参数异常"); // 修改为 "参数异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                cameraService.setEquipmentState(camera.getCameraId(), "未连接");
            } else {
                log.setState(camera.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            cameraLogMapper.insert(log);
        }
    }

    // 备份车检器日志
    private void backupCarDetectorLogs() {
        List<CarDetectorEntity> carDetectors = carDetectorMapper.selectList(null);
        for (CarDetectorEntity carDetector : carDetectors) {
            CarDetectorLogEntity log = new CarDetectorLogEntity();
            log.setCarDetectorId(carDetector.getCarDetectorId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setErrorCode(""); // 修改为 ""
            log.setDetectionRange(carDetector.getDetectionRange());
            log.setEquipmentIp(carDetector.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(carDetector.getCarDetectorName());
            log.setWriterName("System");
            log.setDetectionMethod(carDetector.getDetectionMethod());

            if (carDetector.getDetectionRange() < deviceThresholdsEntity.getCarDetectorRange()) {
                log.setState("未连接");
                log.setDescription("检测范围异常"); // 修改为 "检测范围异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                carDetectorService.setEquipmentState(carDetector.getCarDetectorId(), "未连接");
            } else {
                log.setState(carDetector.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            carDetectorLogMapper.insert(log);
        }
    }

    // 备份入口自助发卡设备日志
    private void backupEntranceEquipmentLogs() {
        List<EntranceEquipmentEntity> entranceEquipments = entranceEquipmentMapper.selectList(null);
        for (EntranceEquipmentEntity entranceEquipment : entranceEquipments) {
            EntranceEquipmentLogEntity log = new EntranceEquipmentLogEntity();
            log.setEntranceEquipmentId(entranceEquipment.getEntranceEquipmentId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setErrorCode(""); // 修改为 ""
            log.setCardNumber(entranceEquipment.getCardNumber());
            log.setEquipmentIp(entranceEquipment.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(entranceEquipment.getEntranceName());
            log.setWriterName("System");

            if (entranceEquipment.getCardNumber() < deviceThresholdsEntity.getCardNumber()) {
                log.setState("未连接");
                log.setDescription("卡片数量异常"); // 修改为 "卡片数量异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                entranceEquipmentService.setEquipmentState(entranceEquipment.getEntranceEquipmentId(), "未连接");
            } else {
                log.setState(entranceEquipment.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            entranceEquipmentLogMapper.insert(log);
        }
    }

    // 备份ETC天线日志
    private void backupEtcAntennaLogs() {
        List<EtcAntennaEntity> etcAntennas = etcAntennaMapper.selectList(null);
        for (EtcAntennaEntity etcAntenna : etcAntennas) {
            EtcAntennaLogEntity log = new EtcAntennaLogEntity();
            log.setAntennaId(etcAntenna.getAntennaId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setErrorCode(""); // 修改为 ""
            log.setFrequency(etcAntenna.getFrequency());
            log.setReadRange(etcAntenna.getReadRange());
            log.setBeamWidth(etcAntenna.getBeamWidth());
            log.setEquipmentIp(etcAntenna.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(etcAntenna.getAntennaName());
            log.setWriterName("System");

            if (etcAntenna.getFrequency() < deviceThresholdsEntity.getEtcFrequency() ||
                    etcAntenna.getReadRange() < deviceThresholdsEntity.getEtcReadRange() ||
                    etcAntenna.getBeamWidth() < deviceThresholdsEntity.getEtcBeamWidth()) {
                log.setState("未连接");
                log.setDescription("参数异常"); // 修改为 "参数异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                etcAntennaService.setEquipmentState(etcAntenna.getAntennaId(), "未连接");
            } else {
                log.setState(etcAntenna.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            etcAntennaLogMapper.insert(log);
        }
    }

    // 备份出口自助缴费设备日志
    private void backupExportPaymentEquipmentLogs() {
        List<ExportPaymentEquipmentEntity> exportEquipments = exportPaymentEquipmentMapper.selectList(null);
        for (ExportPaymentEquipmentEntity exportEquipment : exportEquipments) {
            ExportPaymentEquipmentLogEntity log = new ExportPaymentEquipmentLogEntity();
            log.setExportEquipmentId(exportEquipment.getExportEquipmentId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setErrorCode(""); // 修改为 ""
            log.setReceiptNumber(exportEquipment.getReceiptNumber());
            log.setScannerState(exportEquipment.getScannerState());
            log.setEquipmentIp(exportEquipment.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(exportEquipment.getExportName());
            log.setWriterName("System");

            if (exportEquipment.getReceiptNumber() < deviceThresholdsEntity.getReceiptNumber() ||
                    Float.parseFloat(exportEquipment.getScannerState()) < deviceThresholdsEntity.getScannerState()) {
                log.setState("未连接");
                log.setDescription("参数异常"); // 修改为 "参数异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                exportPaymentEquipmentService.setEquipmentState(exportEquipment.getExportEquipmentId(), "未连接");
            } else {
                log.setState(exportEquipment.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            exportPaymentEquipmentLogMapper.insert(log);
        }
    }

    // 备份诱导屏日志
    private void backupInductionScreenLogs() {
        List<InductionScreenEntity> inductionScreens = inductionScreenMapper.selectList(null);
        for (InductionScreenEntity inductionScreen : inductionScreens) {
            InductionScreenLogEntity log = new InductionScreenLogEntity();
            log.setInductionScreenId(inductionScreen.getInductionScreenId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setErrorCode(""); // 修改为 ""
            log.setBrightness(inductionScreen.getBrightness());
            log.setContrastRatio(inductionScreen.getContrastRatio());
            log.setDisplayRate(inductionScreen.getDisplayRate());
            log.setEquipmentIp(inductionScreen.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(inductionScreen.getInductionScreenName());
            log.setWriterName("System");

            if (inductionScreen.getBrightness() < deviceThresholdsEntity.getInductionBrightness() ||
                    inductionScreen.getContrastRatio() < deviceThresholdsEntity.getInductionContrastRatio() ||
                    inductionScreen.getDisplayRate() < deviceThresholdsEntity.getInductionDisplayRate()) {
                log.setState("未连接");
                log.setDescription("参数异常"); // 修改为 "参数异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                inductionScreenService.setEquipmentState(inductionScreen.getInductionScreenId(), "未连接");
            } else {
                log.setState(inductionScreen.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            inductionScreenLogMapper.insert(log);
        }
    }

    // 备份情报板日志
    private void backupIntelBoardLogs() {
        List<IntelBoardEntity> intelBoards = intelBoardMapper.selectList(null);
        for (IntelBoardEntity intelBoard : intelBoards) {
            IntelBoardLogEntity log = new IntelBoardLogEntity();
            log.setLedBoardId(intelBoard.getLedBoardId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setErrorCode(""); // 修改为 ""
            log.setBrightness(intelBoard.getBrightness());
            log.setContrastRatio(intelBoard.getContrastRatio());
            log.setDisplayRate(intelBoard.getDisplayRate());
            log.setEquipmentIp(intelBoard.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(intelBoard.getLedBoardName());
            log.setWriterName("System");

            if (intelBoard.getBrightness() < deviceThresholdsEntity.getIntelBoardBrightness() ||
                    intelBoard.getContrastRatio() < deviceThresholdsEntity.getIntelBoardContrastRatio() ||
                    intelBoard.getDisplayRate() < deviceThresholdsEntity.getIntelBoardDisplayRate()) {
                log.setState("未连接");
                log.setDescription("参数异常"); // 修改为 "参数异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                intelBoardService.setEquipmentState(intelBoard.getLedBoardId(), "未连接");
            } else {
                log.setState(intelBoard.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            intelBoardLogMapper.insert(log);
        }
    }

    // 备份车道称重设备日志
    private void backupLaneWeighingEquipmentLogs() {
        List<LaneWeighingEquipmentEntity> laneWeighingEquipments = laneWeighingEquipmentMapper.selectList(null);
        for (LaneWeighingEquipmentEntity laneWeighingEquipment : laneWeighingEquipments) {
            LaneWeighingEquipmentLogEntity log = new LaneWeighingEquipmentLogEntity();
            log.setLaneWeighingId(laneWeighingEquipment.getLaneWeighingId());
            log.setLogTime(LocalDateTime.now());
            log.setLogId(UUID.randomUUID().toString());
            log.setWeighingMachineState(laneWeighingEquipment.getWeighingMachineState());
            log.setErrorCode(""); // 修改为 ""
            log.setState(laneWeighingEquipment.getState());
            log.setDisplayState(laneWeighingEquipment.getDisplayState());
            log.setEquipmentIp(laneWeighingEquipment.getEquipmentIp());
            log.setWriterId("000"); // 修改为 "000"
            log.setEquipmentName(laneWeighingEquipment.getLaneWeighingName());
            log.setWriterName("System");

            if (laneWeighingEquipment.getWeighingMachineState() < deviceThresholdsEntity.getWeighingMachineState() ||
                    Float.parseFloat(laneWeighingEquipment.getDisplayState()) < deviceThresholdsEntity.getWeighingDisplayState()) {
                log.setState("未连接");
                log.setDescription("参数异常"); // 修改为 "参数异常"
                log.setLogType("故障日志"); // 修改为 "异常日志"
                laneWeighingEquipmentService.setEquipmentState(laneWeighingEquipment.getLaneWeighingId(), "未连接");
            } else {
                log.setState(laneWeighingEquipment.getState());
                log.setDescription("系统常规检查"); // 修改为 "系统常规检查"
                log.setLogType("常规日志"); // 修改为 "常规日志"
            }

            laneWeighingEquipmentLogMapper.insert(log);
        }
    }
}
