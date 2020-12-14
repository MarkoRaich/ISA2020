
// source: drugAvailability.proto

package com.example.protos;

/**
 * Protobuf type {@code protos.Drug}
 */
public final class Drug extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:protos.Drug)
    DrugOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Drug.newBuilder() to construct.
  private Drug(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Drug() {
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Drug();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Drug(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            id_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 24: {

            quantity_ = input.readInt32();
            break;
          }
          case 34: {
            com.example.protos.Pharmacy.Builder subBuilder = null;
            if (pharmacy_ != null) {
              subBuilder = pharmacy_.toBuilder();
            }
            pharmacy_ = input.readMessage(com.example.protos.Pharmacy.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(pharmacy_);
              pharmacy_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.protos.DrugAvailabilityOuterClass.internal_static_protos_Drug_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.protos.DrugAvailabilityOuterClass.internal_static_protos_Drug_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.protos.Drug.class, com.example.protos.Drug.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public int getId() {
    return id_;
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int QUANTITY_FIELD_NUMBER = 3;
  private int quantity_;
  /**
   * <code>int32 quantity = 3;</code>
   * @return The quantity.
   */
  @java.lang.Override
  public int getQuantity() {
    return quantity_;
  }

  public static final int PHARMACY_FIELD_NUMBER = 4;
  private com.example.protos.Pharmacy pharmacy_;
  /**
   * <code>.protos.Pharmacy pharmacy = 4;</code>
   * @return Whether the pharmacy field is set.
   */
  @java.lang.Override
  public boolean hasPharmacy() {
    return pharmacy_ != null;
  }
  /**
   * <code>.protos.Pharmacy pharmacy = 4;</code>
   * @return The pharmacy.
   */
  @java.lang.Override
  public com.example.protos.Pharmacy getPharmacy() {
    return pharmacy_ == null ? com.example.protos.Pharmacy.getDefaultInstance() : pharmacy_;
  }
  /**
   * <code>.protos.Pharmacy pharmacy = 4;</code>
   */
  @java.lang.Override
  public com.example.protos.PharmacyOrBuilder getPharmacyOrBuilder() {
    return getPharmacy();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (quantity_ != 0) {
      output.writeInt32(3, quantity_);
    }
    if (pharmacy_ != null) {
      output.writeMessage(4, getPharmacy());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (quantity_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, quantity_);
    }
    if (pharmacy_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getPharmacy());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.example.protos.Drug)) {
      return super.equals(obj);
    }
    com.example.protos.Drug other = (com.example.protos.Drug) obj;

    if (getId()
        != other.getId()) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (getQuantity()
        != other.getQuantity()) return false;
    if (hasPharmacy() != other.hasPharmacy()) return false;
    if (hasPharmacy()) {
      if (!getPharmacy()
          .equals(other.getPharmacy())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + QUANTITY_FIELD_NUMBER;
    hash = (53 * hash) + getQuantity();
    if (hasPharmacy()) {
      hash = (37 * hash) + PHARMACY_FIELD_NUMBER;
      hash = (53 * hash) + getPharmacy().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.protos.Drug parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.protos.Drug parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.protos.Drug parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.protos.Drug parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.protos.Drug parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.protos.Drug parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.protos.Drug parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.protos.Drug parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.protos.Drug parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.example.protos.Drug parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.protos.Drug parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.example.protos.Drug parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.example.protos.Drug prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code protos.Drug}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:protos.Drug)
      com.example.protos.DrugOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.protos.DrugAvailabilityOuterClass.internal_static_protos_Drug_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.protos.DrugAvailabilityOuterClass.internal_static_protos_Drug_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.protos.Drug.class, com.example.protos.Drug.Builder.class);
    }

    // Construct using com.example.protos.Drug.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = 0;

      name_ = "";

      quantity_ = 0;

      if (pharmacyBuilder_ == null) {
        pharmacy_ = null;
      } else {
        pharmacy_ = null;
        pharmacyBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.protos.DrugAvailabilityOuterClass.internal_static_protos_Drug_descriptor;
    }

    @java.lang.Override
    public com.example.protos.Drug getDefaultInstanceForType() {
      return com.example.protos.Drug.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.protos.Drug build() {
      com.example.protos.Drug result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.protos.Drug buildPartial() {
      com.example.protos.Drug result = new com.example.protos.Drug(this);
      result.id_ = id_;
      result.name_ = name_;
      result.quantity_ = quantity_;
      if (pharmacyBuilder_ == null) {
        result.pharmacy_ = pharmacy_;
      } else {
        result.pharmacy_ = pharmacyBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.protos.Drug) {
        return mergeFrom((com.example.protos.Drug)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.protos.Drug other) {
      if (other == com.example.protos.Drug.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.getQuantity() != 0) {
        setQuantity(other.getQuantity());
      }
      if (other.hasPharmacy()) {
        mergePharmacy(other.getPharmacy());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.example.protos.Drug parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.example.protos.Drug) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int id_ ;
    /**
     * <code>int32 id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public int getId() {
      return id_;
    }
    /**
     * <code>int32 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      
      id_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private int quantity_ ;
    /**
     * <code>int32 quantity = 3;</code>
     * @return The quantity.
     */
    @java.lang.Override
    public int getQuantity() {
      return quantity_;
    }
    /**
     * <code>int32 quantity = 3;</code>
     * @param value The quantity to set.
     * @return This builder for chaining.
     */
    public Builder setQuantity(int value) {
      
      quantity_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 quantity = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearQuantity() {
      
      quantity_ = 0;
      onChanged();
      return this;
    }

    private com.example.protos.Pharmacy pharmacy_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.example.protos.Pharmacy, com.example.protos.Pharmacy.Builder, com.example.protos.PharmacyOrBuilder> pharmacyBuilder_;
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     * @return Whether the pharmacy field is set.
     */
    public boolean hasPharmacy() {
      return pharmacyBuilder_ != null || pharmacy_ != null;
    }
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     * @return The pharmacy.
     */
    public com.example.protos.Pharmacy getPharmacy() {
      if (pharmacyBuilder_ == null) {
        return pharmacy_ == null ? com.example.protos.Pharmacy.getDefaultInstance() : pharmacy_;
      } else {
        return pharmacyBuilder_.getMessage();
      }
    }
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     */
    public Builder setPharmacy(com.example.protos.Pharmacy value) {
      if (pharmacyBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        pharmacy_ = value;
        onChanged();
      } else {
        pharmacyBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     */
    public Builder setPharmacy(
        com.example.protos.Pharmacy.Builder builderForValue) {
      if (pharmacyBuilder_ == null) {
        pharmacy_ = builderForValue.build();
        onChanged();
      } else {
        pharmacyBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     */
    public Builder mergePharmacy(com.example.protos.Pharmacy value) {
      if (pharmacyBuilder_ == null) {
        if (pharmacy_ != null) {
          pharmacy_ =
            com.example.protos.Pharmacy.newBuilder(pharmacy_).mergeFrom(value).buildPartial();
        } else {
          pharmacy_ = value;
        }
        onChanged();
      } else {
        pharmacyBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     */
    public Builder clearPharmacy() {
      if (pharmacyBuilder_ == null) {
        pharmacy_ = null;
        onChanged();
      } else {
        pharmacy_ = null;
        pharmacyBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     */
    public com.example.protos.Pharmacy.Builder getPharmacyBuilder() {
      
      onChanged();
      return getPharmacyFieldBuilder().getBuilder();
    }
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     */
    public com.example.protos.PharmacyOrBuilder getPharmacyOrBuilder() {
      if (pharmacyBuilder_ != null) {
        return pharmacyBuilder_.getMessageOrBuilder();
      } else {
        return pharmacy_ == null ?
            com.example.protos.Pharmacy.getDefaultInstance() : pharmacy_;
      }
    }
    /**
     * <code>.protos.Pharmacy pharmacy = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.example.protos.Pharmacy, com.example.protos.Pharmacy.Builder, com.example.protos.PharmacyOrBuilder> 
        getPharmacyFieldBuilder() {
      if (pharmacyBuilder_ == null) {
        pharmacyBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.example.protos.Pharmacy, com.example.protos.Pharmacy.Builder, com.example.protos.PharmacyOrBuilder>(
                getPharmacy(),
                getParentForChildren(),
                isClean());
        pharmacy_ = null;
      }
      return pharmacyBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:protos.Drug)
  }

  // @@protoc_insertion_point(class_scope:protos.Drug)
  private static final com.example.protos.Drug DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.protos.Drug();
  }

  public static com.example.protos.Drug getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Drug>
      PARSER = new com.google.protobuf.AbstractParser<Drug>() {
    @java.lang.Override
    public Drug parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Drug(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Drug> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Drug> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.protos.Drug getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

