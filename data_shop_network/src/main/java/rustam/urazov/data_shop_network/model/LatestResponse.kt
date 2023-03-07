package rustam.urazov.data_shop_network.model

import com.google.gson.annotations.SerializedName

data class LatestResponse(
    @SerializedName("latest") val latest: List<ProductResponse>
)