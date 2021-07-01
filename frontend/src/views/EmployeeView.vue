<template>
  <v-card>
    <v-card-title>
      Available Animals
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="animals"
      @click:row="adoptAnimal"
    ></v-data-table>
    <AnimalDialogUser
      :opened="dialogVisible"
      @refresh="refreshList"
    ></AnimalDialogUser>
  </v-card>
</template>

<script>
import api from "../api";
import AnimalDialogUser from "@/components/AnimalDialogUser";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "EmployeeView",
  components: { AnimalDialogUser },
  data() {
    return {
      animals: [],
      message: [],
      search: "",
      headers: [
        {
          text: "Id",
          align: "start",
          value: "id",
        },
        { text: "Species", value: "animal" },
        { text: "Age", value: "age" },
        { text: "Gender", value: "gender" },
        { text: "Size", value: "size" },
        { text: "Description", value: "description" },
      ],
      dialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    adoptAnimal() {
      this.dialogVisible = true;
    },
    adoptedAnimals() {
      api.animals.adoptedAnimals();
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.animals = await api.animals.allAnimals();
    },
  },

  created() {
    this.adoptionSocket = new SockJS("http://localhost:8088/notification");
    this.adoptionStomp = new Stomp.over(this.adoptionSocket);
    this.adoptionStomp.connect({}, (F) => {console.log(F); this.adoptionStomp.subscribe("/adoptionRequest", (G) => {console.log(G.body)})});
    this.refreshList();
  },
};
</script>

<style scoped></style>
