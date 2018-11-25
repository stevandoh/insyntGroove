package com.johnny.behwe.utils

import java.util.HashMap

/**
 * Created by SOVAVY on 4/25/2017.
 */

 object Constants {

    val EMPTY_STRING = ""
    val PREFS = "prefs"
    val prefBooleanDefault = false
    val SHOP_LABEL = "Shop"
    val NAME_LABEL = "Enter Customer Name"
    val SHOP_TYPE_LABEL = "Shop Type"
    val HAWKER_TYPE_LABEL = "Hawker Type"
    val HAWKER_LABEL = "Hawker"
    val HAWKER_STATIONARY_LABEL = "Stationary"
    val SELLING_SPOT_LABEL = "Selling Spot"
    val CUSTOMER_PICTURE_LABEL = "Take Customer Picture"
    val PHONE_LABEL = "Customer Phone Number"
    val SURNAME_LABEL = "Surname"
    val FIRSTNAME_LABEL = "Firstname"
    val SURNAME_DATA_KEY = "surname"
    val FIRSTNAME_DATA_KEY = "firstname"
    val SIMPLE_DATA_KEY = "_"
    val PHONE_DATA_KEY = "phone"
    val NULL = ""
    val OTHER_NAME = "Enter Other Names"
    val MR = "Mr"
    val MRS = "Mrs"
    val DR = "Dr"
    val PROF = "Prof"
    val REV = "Rev"
    val MADAM = "Madam"
    val MISS = "Miss"
    val OTHER = "Other"
    val SGT = "SGT"
    val PHYSICAL_ADDRESS_LABEL = "Physical Address"
    val POSTAL_ADDRESS_LABEL = "Postal Address"
    val BRANCH_LABEL = "Select Branch"
    val SECTOR_LABEL = "Select Sector"
    val CUSTOMER_TYPE_LABEL = "Select Customer Type"
    val PRODUCT_LABEL = "Select Product"
    val TITLE_LABEL = "Select Title"
    val AMOUNT_LABEL = "Amount"
    val REVIEW_LABEL = "Remarks"
    val SIGNATURE_LABEL = "Signature"
    val CUSTOMER_LOCATION = "Enter Customer Location"
    val prefsLogin = "prefslogin"
    val ID_PICTURE_LABEL = "Take ID Picture"
    val ID_TYPE_LABEL = "Select ID Type"
    val VOTER_ID_CHOICE = "Voter ID"
    val DRIVER_LICENCSE_CHOICE = "Driver License"
    val SSNIT_NUMBER_CHOICE = "SSNIT No"
    val STUDENT_ID_CHOICE = "Student ID"
    val NHIS_CHOICE = "NHIS"
    val NATIONAL_ID_CHOICE = "National ID"
    val PASSPORT_CHOICE = "Passport"
    val ID_NUMBER_LABEL = "Enter ID Number"
    val EMAIL_LABEL = "Email"
    val DATE_PICKER_LABEL = "Enter Date of Birth"
    val LOCATION_LABEL = "Get Customer Location"
    val GENDER_LABEL = "Select Gender"
    val GENDER_MALE = "Male"
    val GENDER_FEMALE = "Female"
    val HEAD_OFFICE_CHOICE = "Head Office"
    val EAST_LEGON_CHOICE = "East Legon"
    val KASOA_CHOICE = "Kasoa"
    val KANESHIE_CHOICE = "Kaneshie"
    val TEMA_CHOICE = "Tema"
    val MADINA_CHOICE = "Madina"
    val ACCRA_CHOICE = "Accra"
    val OKAISHIE_CHOICE = "Okaishie"
    val MALLAM_ATTA_CHOICE = "Mallam Atta"
    val AGBOBLOSHIE_CHOICE = "Agbobloshie"
    val TRADEFAIR_CHOICE = "Trade Fair"
    val ASHIAMAN_CHOICE = "Ashiaman"
    val DADIESO_CHOICE = "Dadieso"
    val BONSU_NKWANTA = "Bonsu Nkwantu"
    val DEBISO_CHOICE = "Debiso"
    val BANTAMA_CHOICE = "Bantama"
    val MISCELLANEOUS_CHOICE = "Miscellaneous"
    val AGRICULTURE_AND_FORESTRY_CHOICE = "Agriculture and Forestry Choice"
    val MINING_AND_QUARRING_CHOICE = "Mining and Quarrying Choice"
    val MANUFACTURING_CHOICE = "Manufacturing"
    val CONSTRUCTION_CHOICE = "Construction"
    val ELECTRICITY_GAS_WATER_CHOICE = "Electricity, Gas & Water"
    val INDIVIDUAL_CHOICE = "Individual"
    val COMMERCE_FINANCE_CHOICE = "Commerce & Finance"
    val TRANSPORT_STORAGE_COMMUNICATION_CHOICE = "Transport, Storage and Communication"
    val SERVICES_CHOICE = "Services"
    val CURRENT_ACCOUNT_PERSONAL_CHOICE = "Current Account(Personal)"
    val SAVINGS_ACCOUNT_CHOICE = "Savings Account"
    val SAVING_PLUS_CHOICE = "Savings Plus"
    val branches: MutableMap<String, String>
    val vBranches: MutableMap<String, String>
    val sectors: MutableMap<String, String>
    val customerTypes: MutableMap<String, String>
    val titles: MutableMap<String, String>
    val idTypes: MutableMap<String, String>
    val products: MutableMap<String, String>
    val imageTypes: MutableMap<String, String>
    val genders: MutableMap<String, String>
    val CUSTOMER_PICTURE_CHOICE = "Customer Picture"
    val CUSTOMER_SIGNATURE_CHOICE = "Customer Signature"
    val CUSTOMER_PICTURE_AND_SIGNATURE_CHOICE = "Customer Picture and Signature"
    val CUSTOMER_OTHER_IMAGE = "Customer other image"
    var serverAddress: String? = null
    var noNetworkMsg: Int = 0
    var hasChangedPassword = "hasChangedPassword"
    val KEY_NETWORK_MTN = "mtn-gh"
    val KEY_NETWORK_VODAFONE = "vodaphone-gh"
    val KEY_NETWORK_TIGO = "tigo-gh"
    val KEY_NETWORK_AIRTEL = "airtel-gh"
    val NETWORK_MTN = "MTN Mobile Money"
    val NETWORK_AIRTEL = "Airtel Money"
    val NETWORK_TIGO = "Tigo Cash"
    val NETWORK_VODAFONE = "Vodafone Cash"

    val IS_LOGGED_IN = "IS_LOGGED_IN"
    val ID_TOKEN = "ID_TOKEN"


    val DRAWER_HOME = 0L
    val DRAWER_CUSTOMER_REGISTRATION = 1L
    val DRAWER_REPORTS = 2L
    val DRAWER_DRAW_RESULTS = 3L
    val DRAWER_INVITE_FRIEND = 4L
    val DRAWER_FAQS = 5L
    val DRAWER_CONTACT_US = 6L
    val DRAWER_TRANSACTIONS = 7L
    val DRAWER_T_AND_C = 8L
    val DRAWER_SIGNOUT = 9L
    val DRAWER_PROFILE = 10L
    val DRAWER_WALLET = 11L


    //using static initializers to prepopulate final static branches, sectors,
    // customerTypes, titles, idTypes, products
    init {
        branches = HashMap()
        //        branches.put(HEAD_OFFICE_CHOICE, "0000");
        //        branches.put(EAST_LEGON_CHOICE, "0001");
        //        branches.put(KASOA_CHOICE, "0002");
        //        branches.put(KANESHIE_CHOICE, "0003");
        //        branches.put(TEMA_CHOICE, "0004");
        //        branches.put(MADINA_CHOICE, "0005");
        //        branches.put(ACCRA_CHOICE, "0006");

        //New branches codes
        branches[HEAD_OFFICE_CHOICE] = "0000"
        branches[EAST_LEGON_CHOICE] = "0008"
        branches[OKAISHIE_CHOICE] = "0002"
        branches[MALLAM_ATTA_CHOICE] = "0003"
        branches[AGBOBLOSHIE_CHOICE] = "0011"
        branches[TRADEFAIR_CHOICE] = "0001"
        branches[KASOA_CHOICE] = "0005"
        branches[ASHIAMAN_CHOICE] = "0007"
        branches[DADIESO_CHOICE] = "0009"
        branches[BONSU_NKWANTA] = "0004"
        branches[DEBISO_CHOICE] = "0010"
        branches[BANTAMA_CHOICE] = "0013"
        branches[MADINA_CHOICE] = "0006"


    }

    init {
        vBranches = HashMap()
        vBranches["0000"] = HEAD_OFFICE_CHOICE
        vBranches["0008"] = EAST_LEGON_CHOICE
        vBranches["0002"] = OKAISHIE_CHOICE
        vBranches["0003"] = MALLAM_ATTA_CHOICE
        vBranches["0011"] = AGBOBLOSHIE_CHOICE
        vBranches["0001"] = TRADEFAIR_CHOICE
        vBranches["0005"] = KASOA_CHOICE
        vBranches["0007"] = ASHIAMAN_CHOICE
        vBranches["0009"] = DADIESO_CHOICE
        vBranches["0004"] = BONSU_NKWANTA
        vBranches["0010"] = DEBISO_CHOICE
        vBranches["0013"] = BANTAMA_CHOICE
        vBranches["0006"] = MADINA_CHOICE

    }

    init {
        sectors = HashMap()
        sectors[MISCELLANEOUS_CHOICE] = "01"
        sectors[AGRICULTURE_AND_FORESTRY_CHOICE] = "10"
        sectors[MINING_AND_QUARRING_CHOICE] = "20"
        sectors[MANUFACTURING_CHOICE] = "30"
        sectors[CONSTRUCTION_CHOICE] = "40"
        sectors[ELECTRICITY_GAS_WATER_CHOICE] = "50"
        sectors[COMMERCE_FINANCE_CHOICE] = "60"
        sectors[TRANSPORT_STORAGE_COMMUNICATION_CHOICE] = "70"
        sectors[SERVICES_CHOICE] = "80"
    }

    init {
        customerTypes = HashMap()
        customerTypes[INDIVIDUAL_CHOICE] = "I"
        customerTypes["COMBINED/JOINT"] = "C"
        customerTypes["LEGAL ENTITY"] = "L"
        customerTypes["OTHERS"] = "O"

    }

    init {
        titles = HashMap()
        titles[MR] = "001"
        titles[MRS] = "002"
        titles[DR] = "003"
        titles[PROF] = "004"
        titles[REV] = "005"
        titles[MADAM] = "006"
        titles[MISS] = "007"
        titles[OTHER] = "008"
        //        titles.put(SGT,"009");

    }

    init {
        idTypes = HashMap()
        idTypes[PASSPORT_CHOICE] = "100"
        idTypes[STUDENT_ID_CHOICE] = "200"
        idTypes[NATIONAL_ID_CHOICE] = "300"
        idTypes[NHIS_CHOICE] = "400"
        idTypes[DRIVER_LICENCSE_CHOICE] = "500"
        idTypes[VOTER_ID_CHOICE] = "600"
    }

    init {
        products = HashMap()
        products[CURRENT_ACCOUNT_PERSONAL_CHOICE] = "10"
        products["Current Account(Corporate)"] = "12"
        products["Regular Susu"] = "18"
        products[SAVINGS_ACCOUNT_CHOICE] = "20"
        products["Student Saver Account"] = "21"
        products["Susu Extra"] = "22"
        products["Nnoboa Susu"] = "24"
        products["Ahenfo Susu"] = "25"
        products["Kids Account"] = "27"
        products["Anidaso Account"] = "28"
        products["Daily Micro Investment"] = "29"
        //        products.put(SAVING_PLUS_CHOICE, "70");

    }

    init {
        imageTypes = HashMap()
        imageTypes[CUSTOMER_PICTURE_CHOICE] = "1"
        imageTypes[CUSTOMER_SIGNATURE_CHOICE] = "2"
        imageTypes[CUSTOMER_PICTURE_AND_SIGNATURE_CHOICE] = "3"
        imageTypes[CUSTOMER_OTHER_IMAGE] = "4"


    }

    init {
        genders = HashMap()
        genders[GENDER_MALE] = "M"
        genders[GENDER_FEMALE] = "F"

    }


}