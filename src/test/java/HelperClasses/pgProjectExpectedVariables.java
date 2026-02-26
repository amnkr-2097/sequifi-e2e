package HelperClasses;

import java.util.HashMap;
import java.util.Map;

public class pgProjectExpectedVariables {

    // Variables for Location Tab
    private static final Map<String, String> locState = new HashMap<>();
    private static final Map<String, String> locLocationCode = new HashMap<>();
    private static final Map<String, String> locEffectiveDate = new HashMap<>();
    private static final Map<String, String> locRedlineMin = new HashMap<>();
    private static final Map<String, String> locRedlineMax = new HashMap<>();
    private static final Map<String, String> locRedlineStd = new HashMap<>();
    private static final Map<String, String> locOfficeName = new HashMap<>();
    private static final Map<String, String> locOfficeAddress = new HashMap<>();
    private static final Map<String, String> locInstaller = new HashMap<>();

    // Variables for Department Tab
    private static final Map<String, String> deptParentDepartment = new HashMap<>();
    private static final Map<String, String> deptChildDepartment = new HashMap<>();
    private static final Map<String, String> deptSubDepartment = new HashMap<>();

    // Variables for Tier Tab
    private static final Map<String, String> tierSchemaName = new HashMap<>();
    private static final Map<String, String> tierSchemaDescription = new HashMap<>();
    private static final Map<String, String> tierTierSystem = new HashMap<>();
    private static final Map<String, String> tierTierMetrics = new HashMap<>();
    private static final Map<String, String> tierTierTypes = new HashMap<>();
    private static final Map<String, String> tierTierDuration = new HashMap<>();
    private static final Map<String, String> tierStartAndEnd = new HashMap<>();

    public static void setLocState(String key, String value) {locState.put(key, value);}
    public static String getLocState(String key) {return locState.get(key);}

    public static void setLocLocationCode(String key, String value) {locLocationCode.put(key, value);}
    public static String getLocLocationCode(String key) {return locLocationCode.get(key);}

    public static void setLocEffectiveDate(String key, String value) {locEffectiveDate.put(key, value);}
    public static String getLocEffectiveDate(String key) {return locEffectiveDate.get(key);}

    public static void setLocRedlineMin(String key, String value) {locRedlineMin.put(key, value);}
    public static String getLocRedlineMin(String key) {return locRedlineMin.get(key);}

    public static void setLocRedlineMax(String key, String value) {locRedlineMax.put(key, value);}
    public static String getLocRedlineMax(String key) {return locRedlineMax.get(key);}

    public static void setLocRedlineStd(String key, String value) {locRedlineStd.put(key, value);}
    public static String getLocRedlineStd(String key) {return locRedlineStd.get(key);}

    public static void setLocOfficeNamee(String key, String value) {locOfficeName.put(key, value);}
    public static String getLocOfficeName(String key) {return locOfficeName.get(key);}

    public static void setLocOfficeAddress(String key, String value) {locOfficeAddress.put(key, value);}
    public static String getLocOfficeAddress(String key) {return locOfficeAddress.get(key);}

    public static void setLocInstaller(String key, String value) {locInstaller.put(key, value);}
    public static String getLocInstaller(String key) {return locInstaller.get(key);}

    public static void setDeptParentDepartment(String key, String value) {deptParentDepartment.put(key, value);}
    public static String getDeptParentDepartment(String key) {return deptParentDepartment.get(key);}

    public static void setDeptChildDepartment(String key, String value) {deptChildDepartment.put(key, value);}
    public static String getDeptChildDepartment(String key) {return deptChildDepartment.get(key);}

    public static void setDeptSubDepartment(String key, String value) {deptSubDepartment.put(key, value);}
    public static String getDeptSubDepartment(String key) {return deptSubDepartment.get(key);}

    public static void setTierSchemaName(String key, String value) {tierSchemaName.put(key, value);}
    public static String getTierSchemaName(String key) {return tierSchemaName.get(key);}

    public static void setTierSchemaDescription(String key, String value) {tierSchemaDescription.put(key, value);}
    public static String getTierSchemaDescription(String key) {return tierSchemaDescription.get(key);}

    public static void setTierTierSystem(String key, String value) {tierTierSystem.put(key, value);}
    public static String getTierTierSystem(String key) {return tierTierSystem.get(key);}

    public static void setTierTierMetrics(String key, String value) {tierTierMetrics.put(key, value);}
    public static String getTierTierMetrics(String key) {return tierTierMetrics.get(key);}

    public static void setTierTierTypes(String key, String value) {tierTierTypes.put(key, value);}
    public static String getTierTierTypes(String key) {return tierTierTypes.get(key);}

    public static void setTierTierDuration(String key, String value) {tierTierDuration.put(key, value);}
    public static String getTierTierDuration(String key) {return tierTierDuration.get(key);}

    public static void setTierStartAndEnd(String key, String value) {tierStartAndEnd.put(key, value);}
    public static String getTierStartAndEnd(String key) {return tierStartAndEnd.get(key);}








}
