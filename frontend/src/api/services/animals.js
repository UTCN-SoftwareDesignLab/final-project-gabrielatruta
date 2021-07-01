import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allAnimals() {
        return HTTP.get(BASE_URL + "/animals", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(item) {
        return HTTP.post(BASE_URL + "/animals", item, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(item) {
        return HTTP.put(BASE_URL + "/animals/" + item.id, item, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    deleteAnimal(id) {
        return HTTP.delete(BASE_URL + "/animals/" + id, {
            headers: authHeader(),
        }).then((response) => {
            return response.data;
        });
    },
    adoptedAnimals() {
        return HTTP.get(BASE_URL + "/animals/adopted", {
            responseType: "arraybuffer",
            headers: authHeader(),
        }).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement("a");
            link.href = url;
            link.setAttribute("download", "Adopted_Animals.pdf");
            document.body.appendChild(link);
            link.click();
        });
    }
};