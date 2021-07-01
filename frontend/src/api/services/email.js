import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    sendAdoptionRequest(email) {
        return HTTP.post(BASE_URL + "/email", email, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    }
};