<template>
  <div id="prize-list">
    <h1>Prizes!</h1>
    <table id="tbl-prizes">
      <thead id="tbl-head-prizes">
        <tr>
          <th>&nbsp;</th>
          <th>Prize</th>
          <th>Points to Redeem</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(prize, key) in prizeList" v-bind:key="key">
          <td>
            <input
              type="checkbox"
              name="selectedPrizes"
              v-model="selectedPrizeIDs"
              v-bind:checked="
                selectedPrizeIDs.includes(Number.parseInt(prize.prize_id))
              "
              v-bind:value="parseInt(prize.prize_id)"
            />
          </td>
          <td>{{ prize.prize_name }}</td>
          <td>{{ prize.point_value }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import PrizeService from "../services/PrizeService.js";
export default {
  name: "prize-details",
  data() {
    return {
      prizeList: [],
      selectedPrizeIDs: [],
    };
  },
  created() {
    PrizeService.getAllPrizes().then((response) => {
      this.prizeList = response.data;
    });
  },
};
</script>

<style>
</style>