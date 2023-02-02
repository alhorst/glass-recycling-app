<!--
This will be used for user's account page after logged in!
Three columns( history, calendar, account)

-->

<template>
    <div>
    
    </div>
</template>

<script>

export default {
    name: "AccountMain",
methods:{
    setUser(user){
        this.$store.commit('SET_USER', user)
    }

}
}

</script>