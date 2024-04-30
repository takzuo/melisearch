package com.takzuo.melisearch.model

data class Attribute(
    val attribute_group_id: String,
    val attribute_group_name: String,
    val id: String,
    val name: String,
    val source: Long,
    val value_id: String,
    val value_name: String,
    val value_struct: ValueStruct,
    val value_type: String,
    val values: List<ValueX>
)