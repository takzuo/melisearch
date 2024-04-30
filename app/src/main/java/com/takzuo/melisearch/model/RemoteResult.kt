package com.takzuo.melisearch.model

data class RemoteResult(
    val available_filters: List<AvailableFilter>  = emptyList(),
    val available_sorts: List<AvailableSort>  = emptyList(),
    val country_default_time_zone: String = "",
    val filters: List<Any> = emptyList(),
    val paging: Paging? = null,
    val pdp_tracking: PdpTracking? = null,
    val query: String = "",
    val results: List<Result> = emptyList(),
    val site_id: String = "",
    val sort: Sort? = null
)