<script setup>

defineProps({
    header: {
        type: String,
        required: true,
    },
});
</script>

<template>
    <div>
        <h4>{{header}}</h4>
    </div>
    <div>
        <slot></slot>
    </div>

</template>

<style scoped>

</style>