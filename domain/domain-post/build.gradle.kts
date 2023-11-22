plugins{

}

dependencies{
    implementation(project(":domain:domain-article"))
    implementation(project(mapOf("path" to ":domain:domain-system")))
}