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
      <v-btn @click="sendMail">Send Email</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="animals"
    ></v-data-table>
    <EmailSend
        :opened="dialogVisible"
        @refresh="refreshList"
    ></EmailSend>

  </v-card>
</template>

<script>
import api from "../api";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import EmailSend from "@/components/EmailSend";

export default {
  name: "EmployeeView",
  components: { EmailSend },
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
    };
  },
  methods: {
    sendMail(){
      this.dialogVisible = true;
    },

    async refreshList() {
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
