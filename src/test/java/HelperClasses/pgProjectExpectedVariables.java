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

    // Variables for Milestone Tab
    private static final Map<String, String> mileSchemaName = new HashMap<>();
    private static final Map<String, String> mileSchemaDescription = new HashMap<>();
    private static final Map<String, String> mileMilestoneName_Initial = new HashMap<>();
    private static final Map<String, String> mileMilestoneName_Final = new HashMap<>();
    private static final Map<String, String> mileTriggerDate_Initial = new HashMap<>();
    private static final Map<String, String> mileTriggerDate_Final = new HashMap<>();

    // Variables for Product Tab
    private static final Map<String, String> prodName = new HashMap<>();
    private static final Map<String, String> prodProductId = new HashMap<>();
    private static final Map<String, String> prodDescription = new HashMap<>();
    private static final Map<String, String> prodRedline = new HashMap<>();
    private static final Map<String, String> prodEffectiveDate = new HashMap<>();

    // Variables for Position Tab
    private static final Map<String, String> posPositionName = new HashMap<>();
    private static final Map<String, String> posDescription = new HashMap<>();
    private static final Map<String, String> posPayType = new HashMap<>();
    private static final Map<String, String> posUpfrontPay = new HashMap<>();
    private static final Map<String, String> posDepartment = new HashMap<>();
    private static final Map<String, String> posProduct = new HashMap<>();
    private static final Map<String, String> posTierSchema = new HashMap<>();
    private static final Map<String, String> posMilestoneSchema = new HashMap<>();
    private static final Map<String, String> posEffectiveDate = new HashMap<>();

    /**
     *
     * GETTER & SETTER
     *
     */
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


    public static void setMileSchemaName(String key, String value) {mileSchemaName.put(key, value);}
    public static String getMileSchemaName(String key) {return mileSchemaName.get(key);}

    public static void setMileSchemaDescription(String key, String value) {mileSchemaDescription.put(key, value);}
    public static String getMileSchemaDescription(String key) {return mileSchemaDescription.get(key);}

    public static void setMileMilestoneName_Final(String key, String value) {mileMilestoneName_Final.put(key, value);}
    public static String getMileMilestoneName_Final(String key) {return mileMilestoneName_Final.get(key);}

    public static void setMileMilestoneName_Initial(String key, String value) {mileMilestoneName_Initial.put(key, value);}
    public static String getMileMilestoneName_Initial(String key) {return mileMilestoneName_Initial.get(key);}

    public static void setMileTriggerDate_Final(String key, String value) {mileTriggerDate_Final.put(key, value);}
    public static String getMileTriggerDate_Final(String key) {return mileTriggerDate_Final.get(key);}

    public static void setMileTriggerDate_Initial(String key, String value) {mileTriggerDate_Initial.put(key, value);}
    public static String getMileTriggerDate_Initial(String key) {return mileTriggerDate_Initial.get(key);}

    // Product getters and setters
    public static void setProdName(String key, String value) {prodName.put(key, value);}
    public static String getProdName(String key) {return prodName.get(key);}

    public static void setProdProductId(String key, String value) {prodProductId.put(key, value);}
    public static String getProdProductId(String key) {return prodProductId.get(key);}

    public static void setProdDescription(String key, String value) {prodDescription.put(key, value);}
    public static String getProdDescription(String key) {return prodDescription.get(key);}

    public static void setProdRedline(String key, String value) {prodRedline.put(key, value);}
    public static String getProdRedline(String key) {return prodRedline.get(key);}

    public static void setProdEffectiveDate(String key, String value) {prodEffectiveDate.put(key, value);}
    public static String getProdEffectiveDate(String key) {return prodEffectiveDate.get(key);}

    // Position getters and setters
    public static void setPosPositionName(String key, String value) {posPositionName.put(key, value);}
    public static String getPosPositionName(String key) {return posPositionName.get(key);}

    public static void setPosDescription(String key, String value) {posDescription.put(key, value);}
    public static String getPosDescription(String key) {return posDescription.get(key);}

    public static void setPosPayType(String key, String value) {posPayType.put(key, value);}
    public static String getPosPayType(String key) {return posPayType.get(key);}

    public static void setPosUpfrontPay(String key, String value) {posUpfrontPay.put(key, value);}
    public static String getPosUpfrontPay(String key) {return posUpfrontPay.get(key);}

    public static void setPosDepartment(String key, String value) {posDepartment.put(key, value);}
    public static String getPosDepartment(String key) {return posDepartment.get(key);}

    public static void setPosProduct(String key, String value) {posProduct.put(key, value);}
    public static String getPosProduct(String key) {return posProduct.get(key);}

    public static void setPosTierSchema(String key, String value) {posTierSchema.put(key, value);}
    public static String getPosTierSchema(String key) {return posTierSchema.get(key);}

    public static void setPosMilestoneSchema(String key, String value) {posMilestoneSchema.put(key, value);}
    public static String getPosMilestoneSchema(String key) {return posMilestoneSchema.get(key);}

    public static void setPosEffectiveDate(String key, String value) {posEffectiveDate.put(key, value);}
    public static String getPosEffectiveDate(String key) {return posEffectiveDate.get(key);}

}
