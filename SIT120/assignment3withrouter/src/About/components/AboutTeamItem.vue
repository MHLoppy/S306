<script setup>

defineProps({
    name: {
        type: String,
        required: true,
    },
    imageLink: {
        type: String,
        required: true,
    },
});
</script>

<template>
    <div>
        <h4>{{name}}</h4>
    </div>
    <div class="container">
        <div id="picture">
            <!-- auto-completing the path saves a bit of effort from typing the full one each time -->
            <img :src="'src/About/assets/' + imageLink">
        </div>
        <div>
            <slot></slot>
        </div>
    </div>

</template>

<style scoped>
img {
    filter: brightness(85%);
    margin-right: 20px;
}

.container {
    width: 400px;
    display: flex;
    flex-direction: row;
}

</style>