<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
         Send Email
        </v-toolbar>
        <v-form>
          <v-text-field
              v-model="subject"
              label="Subject"
              required
          ></v-text-field>

          <v-text-field
              v-model="message"
              label="Message"
              required
          ></v-text-field>

        </v-form>
        <v-card-actions>
          <v-btn @click="sendEmail">Send</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "EmailSend",
  props: {
    opened: Boolean,
  },
  data: () => ({
    subject: '',
    message: '',
  }),
  methods: {
    sendEmail() {
      api.email.sendAdoptionRequest({
        subject: this.subject,
        message: this.message,
      }).then(() => this.$emit("refresh"));
    },
  },

};
</script>

<style scoped></style>
