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
      <v-btn @click="sendSMS">Send SMS</v-btn>
      <div>
        <stripe-checkout
            ref="checkoutRef"
            mode="payment"
            :pk="publishableKey"
            :line-items="lineItems"
            :success-url="successURL"
            :cancel-url="cancelURL"
            @loading="v => loading = v"
        />
        <v-btn @click="donate">Donate</v-btn>
      </div>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="animals"
    ></v-data-table>
    <EmailSend
        :opened="dialogVisible"
        @refresh="refreshList"
    ></EmailSend>
    <SMSSend
        :opened="smsVisible"
        @refresh="refreshList"
    ></SMSSend>

  </v-card>
</template>

<script>
import api from "../api";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import EmailSend from "@/components/EmailSend";
import SMSSend from "@/components/SMSSend";
import { StripeCheckout } from '@vue-stripe/vue-stripe';

export default {
  name: "EmployeeView",
  components: { EmailSend, SMSSend, StripeCheckout },
  data() {
    this.publishableKey = "pk_test_51J89IeBj7EdWDUaU9ZzZauP9L1Uwo5CJu9KYzVbeFlf8nOvpbGc8a8TVuBDfRi3xGqJR1tHhQnRQU9dZ57BTJ0NW00WqL4CysV";
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
      loading: false,
      lineItems: [
        {
          price: "price_1J8LZgBj7EdWDUaUpQgayd9l",
          quantity: 1,
        },
      ],
      successURL: 'http://localhost:8091/#/regularUser',
      cancelURL: 'http://localhost:8091/#/regularUser',
      dialogVisible: false,
      smsVisible: false,
    };
  },
  methods: {
    sendMail(){
      this.dialogVisible = true;
    },
    sendSMS(){
      this.smsVisible = true;
    },
    donate() {
      // You will be redirected to Stripe's secure checkout page
      this.$refs.checkoutRef.redirectToCheckout();
    },

    async refreshList() {
      this.dialogVisible = false;
      this.smsVisible = false;
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
