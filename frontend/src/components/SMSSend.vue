<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          Send SMS
        </v-toolbar>
        <v-form>
          <v-text-field
              v-model="receiver"
              label="TO"
              required
          ></v-text-field>
          <v-text-field
              v-model="message"
              label="Message"
              required
          ></v-text-field>

        </v-form>
        <v-card-actions>
          <v-btn @click="sendSMS">Send</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "SMSSend",
  props: {
    opened: Boolean,
  },
  data: () => ({
    receiver: '+40784780820 (ex)',
    message: '',
  }),
  methods: {
    sendSMS() {
      api.sms.sendSMS({
        receiver: this.receiver,
        message: this.message,
      }).then(() => this.$emit("refresh"));
    },
  },

};
</script>

<style scoped></style>
