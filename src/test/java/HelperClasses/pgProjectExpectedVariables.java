package HelperClasses;

import java.util.HashMap;
import java.util.Map;

public class pgProjectExpectedVariables {

    // Variables for Location Tab
    private static final Map<String, String> locState = new HashMap<>();
    private static final Map<String, String> locLocationCode = new HashMap<>();
    private static final Map<String, String> locEffectiveDate = new HashMap<>();
    private static final Map<String, String> locRedlineMin = new HashMap<>();
    private static final Map<String, String> locRedlineMax = new HashMap<>  ();
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
    // ── Position Setup Variables ─────────────────────────────────────────────────

    // Wages
    private static final Map<String, String> posSetupWagesEnabled       = new HashMap<>();
    private static final Map<String, String> posSetupWagesType          = new HashMap<>();
    private static final Map<String, String> posSetupWagesAmount        = new HashMap<>();

    // Commission —  Product
    private static final Map<String, String> posSetupCommissionEnabled  = new HashMap<>();
    private static final Map<String, String> posSetupCommProduct = new HashMap<>();
    private static final Map<String, String> posSetupCommType    = new HashMap<>();
    private static final Map<String, String> posSetupCommAmount  = new HashMap<>();
    private static final Map<String, String> posSetupCommTier    = new HashMap<>();
    private static final Map<String, String> posSetupCommAdvance = new HashMap<>();


    // Upfront —  Product
    private static final Map<String, String> posSetupUpfrontEnabled     = new HashMap<>();
    private static final Map<String, String> posSetupUpfProduct  = new HashMap<>();
    private static final Map<String, String> posSetupUpfType     = new HashMap<>();
    private static final Map<String, String> posSetupUpfAmount   = new HashMap<>();
    private static final Map<String, String> posSetupUpfTier     = new HashMap<>();
    private static final Map<String, String> posSetupUpfAdvance  = new HashMap<>();

    // Override —  Product
    private static final Map<String, String> posSetupOverrideEnabled    = new HashMap<>();
    private static final Map<String, String> posSetupOvrProduct  = new HashMap<>();
    private static final Map<String, String> posSetupOvrType     = new HashMap<>();
    private static final Map<String, String> posSetupOvrAmount   = new HashMap<>();
    private static final Map<String, String> posSetupOvrTier     = new HashMap<>();
    private static final Map<String, String> posSetupOvrAdvance  = new HashMap<>();

    //  Tab
    private static final Map<String, String> posSetupDefCommType        = new HashMap<>();
    private static final Map<String, String> posSetupDefCommAmount      = new HashMap<>();
    private static final Map<String, String> posSetupDefUpfType         = new HashMap<>();
    private static final Map<String, String> posSetupDefUpfAmount       = new HashMap<>();
    private static final Map<String, String> posSetupDefOvrType         = new HashMap<>();
    private static final Map<String, String> posSetupDefOvrAmount       = new HashMap<>();

    // Variables for Hire Rep
    private static final Map<String, String> hireFirstName = new HashMap<>();
    private static final Map<String, String> hireLastName = new HashMap<>();
    private static final Map<String, String> hireEmail = new HashMap<>();
    private static final Map<String, String> hirePhone = new HashMap<>();
    private static final Map<String, String> hireState = new HashMap<>();
    private static final Map<String, String> hireOffice = new HashMap<>();
    private static final Map<String, String> hireDepartment = new HashMap<>();
    private static final Map<String, String> hirePosition = new HashMap<>();
    private static final Map<String, String> hireManager = new HashMap<>();
    private static final Map<String, String> hireSetterRedline = new HashMap<>();
    private static final Map<String, String> hireCloserRedline = new HashMap<>();
    private static final Map<String, String> hireSelfGenRedline = new HashMap<>();

    // Variables for Sale
    private static final Map<String, String> saleCustomerName = new HashMap<>();
    private static final Map<String, String> saleID = new HashMap<>();
    private static final Map<String, String> saleKW = new HashMap<>();
    private static final Map<String, String> saleEPC = new HashMap<>();
    private static final Map<String, String> saleNetEPC = new HashMap<>();



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

    public static void setPosEffectiveDate(String key, String value) {posEffectiveDate.put(key, value);}
    public static String getPosEffectiveDate(String key) {return posEffectiveDate.get(key);}

    public static void setPosSetupWagesEnabled(String key, String v)       { posSetupWagesEnabled.put(key, v); }
    public static String getPosSetupWagesEnabled(String key)               { return posSetupWagesEnabled.get(key); }
    public static void setPosSetupWagesType(String key, String v)          { posSetupWagesType.put(key, v); }
    public static String getPosSetupWagesType(String key)                  { return posSetupWagesType.get(key); }
    public static void setPosSetupWagesAmount(String key, String v)        { posSetupWagesAmount.put(key, v); }
    public static String getPosSetupWagesAmount(String key)                { return posSetupWagesAmount.get(key); }

    public static void setPosSetupCommissionEnabled(String key, String v)  { posSetupCommissionEnabled.put(key, v); }
    public static String getPosSetupCommissionEnabled(String key)          { return posSetupCommissionEnabled.get(key); }
    public static void setPosSetupCommProduct(String key, String v) { posSetupCommProduct.put(key, v); }
    public static String getPosSetupCommProduct(String key)         { return posSetupCommProduct.get(key); }

    public static void setPosSetupCommTypeSetter(String key, String v)    { posSetupCommType.put(key, v); }
    public static String getPosSetupCommTypeSetter(String key)            { return posSetupCommType.get(key); }
    public static void setPosSetupCommAmountSetter(String key, String v)  { posSetupCommAmount.put(key, v); }
    public static String getPosSetupCommAmountSetter(String key)          { return posSetupCommAmount.get(key); }


    public static void setPosSetupCommTypeCloser(String key, String v)    { posSetupCommType.put(key, v); }
    public static String getPosSetupCommTypeCloser(String key)            { return posSetupCommType.get(key); }
    public static void setPosSetupCommAmountCloser(String key, String v)  { posSetupCommAmount.put(key, v); }
    public static String getPosSetupCommAmountCloser(String key)          { return posSetupCommAmount.get(key); }


    public static void setPosSetupCommTypeSelfGen(String key, String v)    { posSetupCommType.put(key, v); }
    public static String getPosSetupCommTypeSelfGen(String key)            { return posSetupCommType.get(key); }
    public static void setPosSetupCommAmountSelfGen(String key, String v)  { posSetupCommAmount.put(key, v); }
    public static String getPosSetupCommAmountSelfGen(String key)          { return posSetupCommAmount.get(key); }

    public static void setPosSetupCommTier(String key, String v)    { posSetupCommTier.put(key, v); }
    public static String getPosSetupCommTier(String key)            { return posSetupCommTier.get(key); }
    public static void setPosSetupCommAdvance(String key, String v) { posSetupCommAdvance.put(key, v); }
    public static String getPosSetupCommAdvance(String key)         { return posSetupCommAdvance.get(key); }


    // Commission Tab — Per-product panel fields (Percent, Calculated, Unlock Hiring, Limit, Limit Type)
    private static final Map<String, String> posSetupCommPercent   = new HashMap<>();
    private static final Map<String, String> posSetupCommCalculated = new HashMap<>();
    private static final Map<String, String> posSetupCommLimit      = new HashMap<>();
    private static final Map<String, String> posSetupCommLimitType  = new HashMap<>();

    public static void setPosSetupCommPercent(String k, String v)    { posSetupCommPercent.put(k, v); }
    public static String getPosSetupCommPercent(String k)            { return posSetupCommPercent.get(k); }

    public static void setPosSetupCommCalculated(String k, String v) { posSetupCommCalculated.put(k, v); }
    public static String getPosSetupCommCalculated(String k)         { return posSetupCommCalculated.get(k); }

    public static void setPosSetupCommLimit(String k, String v)      { posSetupCommLimit.put(k, v); }
    public static String getPosSetupCommLimit(String k)              { return posSetupCommLimit.get(k); }

    public static void setPosSetupCommLimitType(String k, String v)  { posSetupCommLimitType.put(k, v); }
    public static String getPosSetupCommLimitType(String k)          { return posSetupCommLimitType.get(k); }

    // Hire Rep getters and setters
    public static void setHireFirstName(String key, String value) {hireFirstName.put(key, value);}
    public static String getHireFirstName(String key) {return hireFirstName.get(key);}

    public static void setHireLastName(String key, String value) {hireLastName.put(key, value);}
    public static String getHireLastName(String key) {return hireLastName.get(key);}

    public static void setHireEmail(String key, String value) {hireEmail.put(key, value);}
    public static String getHireEmail(String key) {return hireEmail.get(key);}

    public static void setHirePhone(String key, String value) {hirePhone.put(key, value);}
    public static String getHirePhone(String key) {return hirePhone.get(key);}

    public static void setHireState(String key, String value) {hireState.put(key, value);}
    public static String getHireState(String key) {return hireState.get(key);}

    public static void setHireOffice(String key, String value) {hireOffice.put(key, value);}
    public static String getHireOffice(String key) {return hireOffice.get(key);}

    public static void setHireManager(String key, String value) {hireManager.put(key, value);}
    public static String getHireManager(String key) {return hireManager.get(key);}

    public static void setHireSetterRedline(String key, String value) {hireSetterRedline.put(key, value);}
    public static String getHireSetterRedline(String key) {return hireSetterRedline.get(key);}

    public static void setHireCloserRedline(String key, String value) {hireCloserRedline.put(key, value);}
    public static String getHireCloserRedline(String key) {return hireCloserRedline.get(key);}

    public static void setHireSelfGenRedline(String key, String value) {hireSelfGenRedline.put(key, value);}
    public static String getHireSelfGenRedline(String key) {return hireSelfGenRedline.get(key);}


    // Sale getters and setters
    public static void setSaleCustomerName(String k, String v) { saleCustomerName.put(k, v); }
    public static String getSaleCustomerName(String k)         { return saleCustomerName.get(k); }

    public static void setSaleID(String k, String v) { saleID.put(k, v); }
    public static String getSaleID(String k)         { return saleID.get(k); }

    public static void setSaleKW(String k, String v) { saleKW.put(k, v); }
    public static String getSaleKW(String k)         { return saleKW.get(k); }

    public static void setSaleEPC(String k, String v) { saleEPC.put(k, v); }
    public static String getSaleEPC(String k)         { return saleEPC.get(k); }

    public static void setSaleNetEPC(String k, String v) { saleNetEPC.put(k, v); }
    public static String getSaleNetEPC(String k)         { return saleNetEPC.get(k); }

}
