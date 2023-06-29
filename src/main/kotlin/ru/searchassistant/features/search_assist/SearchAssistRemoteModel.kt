package ru.searchassistant.features.search_assist

import kotlinx.serialization.Serializable

@Serializable
data class SearchAssistResponseRemote(
    var postal_code: String? = null,
    var country: String? = null,
    var country_iso_code: String? = null,
    var federal_district: String? = null,
    var region_fias_id: String? = null,
    var region_kladr_id: String? = null,
    var region_iso_code: String? = null,
    var region_with_type: String? = null,
    var region_type: String? = null,
    var region_type_full: String? = null,
    var region: String? = null,
    var area_fias_id: String? = null,
    var area_kladr_id: String? = null,
    var area_with_type: String? = null,
    var area_type: String? = null,
    var area_type_full: String? = null,
    var area: String? = null,
    var city_fias_id: String? = null,
    var city_kladr_id: String? = null,
    var city_with_type: String? = null,
    var city_type: String? = null,
    var city_type_full: String? = null,
    var city: String? = null,
    var city_area: String? = null,
    var city_district_fias_id: String? = null,
    var city_district_kladr_id: String? = null,
    var city_district_with_type: String? = null,
    var city_district_type: String? = null,
    var city_district_type_full: String? = null,
    var city_district: String? = null,
    var settlement_fias_id: String? = null,
    var settlement_kladr_id: String? = null,
    var settlement_with_type: String? = null,
    var settlement_type: String? = null,
    var settlement_type_full: String? = null,
    var settlement: String? = null,
    var street_fias_id: String? = null,
    var street_kladr_id: String? = null,
    var street_with_type: String? = null,
    var street_type: String? = null,
    var street_type_full: String? = null,
    var street: String? = null,
    var stead_fias_id: String? = null,
    var stead_cadnum: String? = null,
    var stead_type: String? = null,
    var stead_type_full: String? = null,
    var stead: String? = null,
    var house_fias_id: String? = null,
    var house_kladr_id: String? = null,
    var house_cadnum: String? = null,
    var house_type: String? = null,
    var house_type_full: String? = null,
    var house: String? = null,
    var block_type: String? = null,
    var block_type_full: String? = null,
    var block: String? = null,
    var entrance: String? = null,
    var floor: String? = null,
    var flat_fias_id: String? = null,
    var flat_cadnum: String? = null,
    var flat_type: String? = null,
    var flat_type_full: String? = null,
    var flat: String? = null,
    var flat_area: String? = null,
    var square_meter_price: String? = null,
    var flat_price: String? = null,
    var room_fias_id: String? = null,
    var room_cadnum: String? = null,
    var room_type: String? = null,
    var room_type_full: String? = null,
    var room: String? = null,
    var postal_box: String? = null,
    var fias_id: String? = null,
    var fias_code: String? = null,
    var fias_level: String? = null,
    var fias_actuality_state: String? = null,
    var kladr_id: String? = null,
    var geoname_id: String? = null,
    var capital_marker: String? = null,
    var okato: String? = null,
    var oktmo: String? = null,
    var tax_office: String? = null,
    var tax_office_legal: String? = null,
    var timezone: String? = null,
    var geo_lat: String? = null,
    var geo_lon: String? = null,
    var beltway_hit: String? = null,
    var beltway_distance: String? = null,
    var metro: String? = null,
    var divisions: String? = null,
    var qc_geo: String? = null,
    var qc_complete: String? = null,
    var qc_house: String? = null,
    var history_values: ArrayList<String>? = null,
    var unparsed_parts: String? = null,
    var source: String? = null,
    var qc: String? = null

)

@Serializable
data class SearchAssistReceiveRemote(
    val query: String
)