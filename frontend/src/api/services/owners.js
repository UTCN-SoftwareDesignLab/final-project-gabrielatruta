import authHeader, { BASE_URL, HTTP } from "../http";

export default {

    allOwners() {
        return HTTP.get(BASE_URL + "/owners", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(item) {
        return HTTP.post(BASE_URL + "/owners", item, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(item) {
        return HTTP.put(BASE_URL + "/owners/" + item.id, item, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    deleteOwner(id) {
        return HTTP.delete(BASE_URL + "/owners/" + id, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },

};