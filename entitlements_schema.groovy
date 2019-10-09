system.graph("entitlements").ifNotExists().create()

:remote config alias g entitlements.g

schema.vertexLabel("party").ifNotExists().partitionBy("party_id", Int).property("full_name", Text).property("email", Text).property("created_at", Date).property("updated_at").create()
schema.vertexLabel("identity").ifNotExists().partitionBy("identity_id", Int).property("name", Text).property("created_at", Date).create()

schema.vertexLabel("is_a").tableName("is_a").ifNotExists().from("party").to("identity").partitionBy(OUT, "party_id", "party_id").clusterBy(IN, "identity_id", "identity_id").property("created_at", Date).create()
