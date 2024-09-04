<template>
    <div>
        <h1>My Movies page</h1>
    </div>
  
</template>

<script>
import MovieService from '../services/MovieService';
export default {
data() {
    return {
        movie : {},
    }
},
methods: {
    getMovies(){
        MovieService.getMovies(this.movie)
        .then((response) => {
            console.log(response)
        })
    }
}
}
</script>

<style>

</style>