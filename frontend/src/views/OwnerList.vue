<template>
  <v-card>
    <v-card-title>
      Owners
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>

      <v-btn @click="addOwner">Add Owner</v-btn>
      <v-btn @click="back">Back</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="owners"
        :search="search"
        @click:row="editOwner"
    ></v-data-table>
    <OwnerDialog
        :opened="dialogVisible"
        :owner="selectedItem"
        @refresh="refreshList"
    ></OwnerDialog>
  </v-card>
</template>

<script>
import api from "../api";
import router from "@/router";
import OwnerDialog from "@/components/OwnerDialog";

export default {
  name: "OwnerList",
  components: { OwnerDialog },
  data() {
    return {
      owners: [],
      search: "",
      headers: [
        {
          text: "ID",
          align: "start",
          value: "id",
        },
        { text: "Name", value: "name" },
        { text: "Address", value: "address" },
        { text: "CNP", value: "cnp" },
        { text: "Numerical Code", value: "numericalCode" },
        { text: "Phone", value: "phone" },

      ],
      dialogVisible: false,
      dialogCreate: false,
      selectedItem: {},
    };
  },
  methods: {
    editOwner(owner) {
      this.selectedItem = owner;
      this.dialogVisible = true;
    },
    addOwner() {
      this.dialogVisible = true;
    },

    back() {
      router.push("/users");
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.owners = await api.owners.allOwners();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
