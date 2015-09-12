<#if ejb3>
<#if pojo.hasIdentifierProperty()>
<#if property.equals(clazz.identifierProperty)>
 ${pojo.generateAnnIdGenerator()}
<#-- if this is the id property (getter)-->
<#-- explicitly set the column name for this property-->
</#if>
</#if>

<#if c2h.isOneToOne(property)>
${pojo.generateOneToOneAnnotation(property, cfg)}
<#elseif c2h.isManyToOne(property)>
${pojo.generateManyToOneAnnotation(property)}
<#--TODO support optional and targetEntity-->    
${pojo.generateJoinColumnsAnnotation(property, cfg)}
<#elseif c2h.isCollection(property)>

<#if property.name == "productses">
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "productlines")
<#else>
${pojo.generateCollectionAnnotation(property, cfg)}
</#if>

<#else>
${pojo.generateBasicAnnotation(property)}
${pojo.generateAnnColumnAnnotation(property)}
<#if property.name == "image">
  @Stereotype("PHOTO")
</#if>
</#if>
</#if>