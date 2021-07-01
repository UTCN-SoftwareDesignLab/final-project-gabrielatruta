<template>
  <v-card>
    <v-card-title>
      Animals
      <v-spacer></v-spacer>
      <v-btn @click="createA">Create Animal</v-btn>
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
      <v-btn @click="adoptedAnimals">Adopted Animals</v-btn>
      <v-btn @click="backButton">Back</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="animals"
        @click:row="editAnimal"
    ></v-data-table>
    <AnimalDialogAdmin
        :opened="dialogVisible"
        :item="selectedItem"
        @refresh="refreshList"
    ></AnimalDialogAdmin>
  </v-card>
</template>

<script>
import api from "../api";
import AnimalDialogAdmin from "@/components/AnimalDialogAdmin";
import router from "@/router";


export default {
  name: "AdminView",
  components: { AnimalDialogAdmin},
  data() {
    return {
      animals: [],
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
    editAnimal(item) {
      this.selectedItem = item;
      this.dialogVisible = true;
    },
    adoptedAnimals() {
      api.animals.adoptedAnimals();
    },
    backButton() {
      router.push("/users");
    },
    createA() {
      this.dialogVisible = true;
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.animals = await api.animals.allAnimals();
    },
  },

  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
