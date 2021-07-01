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
            {{ isNew ? "Create animal" : "Edit animal" }}
          </v-toolbar>
          <v-text-field v-model="item.animal" label="Species" />
          <v-text-field v-model="item.name" label="Name" />
          <v-text-field v-model="item.age" label="Age" />
          <v-text-field v-model="item.gender" label="Gender" />
          <v-text-field v-model="item.size" label="Size" />
          <v-text-field v-model="item.description" label="Description" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Edit" }}
          </v-btn>
          <v-btn v-if="!isNew" @click="deleteAnimal">Delete Animal</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "AnimalDialogAdmin",
  props: {
    item: Object,
    opened: Boolean,
  },
  methods: {
    persist(){
      if (this.isNew){
        api.animals
            .create({
              animal: this.item.animal,
              name: this.item.name,
              age: this.item.age,
              gender: this.item.gender,
              size: this.item.size,
              description: this.item.description,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.animals
            .edit({
              id: this.item.id,
              animal: this.item.animal,
              name: this.item.name,
              age: this.item.age,
              gender: this.item.gender,
              size: this.item.size,
              description: this.item.description,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    // editAnimal() {
    //   api.animals
    //       .edit(this.item)
    //       .then(() => this.$emit("refresh"));
    // },
    deleteAnimal() {
      api.animals.deleteAnimal(this.item.id).then(() => this.$emit("refresh"));
    },
  },
  computed: {
    isNew: function () {
      return !this.item.id;
    },
  },
};
</script>

<style scoped></style>
