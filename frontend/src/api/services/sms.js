import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    sendSMS(sms) {
        return HTTP.post(BASE_URL + "/sms", sms, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    }
};