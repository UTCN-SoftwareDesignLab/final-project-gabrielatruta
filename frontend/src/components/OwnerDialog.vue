<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-form>
          <v-toolbar color="primary" dark>
            {{ isNew ? "Create owner" : "Edit owner" }}
          </v-toolbar>
          <v-text-field v-model="owner.name" label="Name" />
          <v-text-field v-model="owner.cnp" label="CNP" />
          <v-text-field v-model="owner.numericalCode" label="Numerical Code" />
          <v-text-field v-model="owner.address" label="address" />
          <v-text-field v-model="owner.phone" label="Phone" />
          <v-text-field v-model="owner.animalID" label="Animal ID" />

        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Edit" }}
          </v-btn>
          <v-btn v-if="!isNew" @click="deleteOwner">Delete Owner</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "OwnerDialog",
  props: {
    owner: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew){
        api.owners
            .create({
              name: this.owner.name,
              address: this.owner.address,
              cnp: this.owner.cnp,
              numericalCode: this.owner.numericalCode,
              phone: this.owner.phone,
              animalID: this.owner.animalID,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.owners
            .edit({
              id: this.owner.id,
              name: this.owner.name,
              address: this.owner.address,
              cnp: this.owner.cnp,
              numericalCode: this.owner.numericalCode,
              phone: this.owner.phone,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    deleteOwner() {
      api.owners.deleteOwner(this.owner.id).then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.owner.id;
    },
  },
};
</script>

<style scoped></style>
