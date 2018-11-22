

/**
 * Created by Joseph on 8/28/2017.
 * Edited by Lexis on 08/30/2017
 */

object ConstantApi {

    //    {"response_code":0,"ledger_balance":1163.75,"available_balance":1158.75,"transactionId":"3013807409837702"}
    //    public static final String LIVE_URL = "http://196.61.32.162:3100/api/";
    val NEW_TEST_URL = "http://msltestapi.midland.com.gh:5100/api/"
    //    public static final String BASE_URL = LIVE_URL;
    val BASE_URL = NEW_TEST_URL
    val CUSTOMER_REGISTRATION_URL = BASE_URL + "customers/register"
    val ADD_CUSTOMER_IMAGE_URL = BASE_URL + "customers/add-image"
    val GET_CUSTOMER_BY_ID = BASE_URL + "customers/id"
    val GET_CUSTOMER_BY_EMAIL = BASE_URL + "customers/email"
    val GET_CUSTOMER_BY_PHONE_NUMBER = BASE_URL + "customers/phone"
    val DEPOSIT_URL = BASE_URL + "accounts/deposit"
    val LOGIN_URL = BASE_URL + "auth/login/agent"
    val GET_ACCOUNT_BALANCE = BASE_URL + "accounts/check-request"
    val GET_ACCOUNT_BALANCE_FOR_AGENT = BASE_URL + "accounts"
    val CHANGE_PASSWORD_URL = BASE_URL + "agents/password/reset"
    val GET_ACCOUNT_BALANCE_FOR_CUSTOMER = BASE_URL + "accounts"
    val URL_SAVE_LOCATION = BASE_URL + "locations"
    val AGENT_UPDATE_URL = BASE_URL + "agents"
    val ADD_ACCOUNT_URL = BASE_URL + "customers/account/add"
    val ADD_RELATION_URL = BASE_URL + "customers/add-customer-relation"

    val AGENT_TRANSACTION_DETAILS_URL = BASE_URL + "agents/"
    val URL_SETTINGS = BASE_URL + "settings"

    fun getAgentTotalNumberOfDeposits(agentId: String): String {
        return String.format("%s%s/portfolio/total", AGENT_TRANSACTION_DETAILS_URL, agentId)
    }


    fun getAgentTotalCollected(agentId: String): String {
        return String.format("%s%s/portfolio/amounts", AGENT_TRANSACTION_DETAILS_URL, agentId)
    }

    fun getAgentCustomers(agentId: String): String {
        return String.format("%s%s/customers", AGENT_TRANSACTION_DETAILS_URL, agentId)
    }

    fun getCustomerByAccountNumber(account: String): String {
        return String.format("%s/%s/list", GET_ACCOUNT_BALANCE_FOR_AGENT, account)
    }

}
