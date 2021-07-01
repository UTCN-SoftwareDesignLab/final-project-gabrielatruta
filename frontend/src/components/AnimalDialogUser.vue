<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-form>
          <v-toolbar color="primary" dark>Adopt Animal</v-toolbar>
          <v-text-field v-model="item.animal" label="Species" />
          <v-text-field v-model="item.name" label="Name" />
          <v-text-field v-model="item.age" label="Age" />
          <v-text-field v-model="item.gender" label="Gender" />
          <v-text-field v-model="item.size" label="Size" />
          <v-text-field v-model="item.description" label="Description" />
        </v-form>
        <v-card-actions>
          <v-btn @click="adoptAnimal">Adopt</v-btn>

        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "AnimalDialogUser",
  props: {
    item: Object,
    opened: Boolean,
  },
  methods: {
    adoptAnimal() {
      api.animals.sendAdoptionRequest().then(() => this.$emit("refresh"));
    },

  },
};
</script>

<style scoped></style>
