// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/proto/com/fzc/domain/Person.proto

package com.fzc.domain.proto;

public final class OuterPersonProto {
  private OuterPersonProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PersonProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PersonProto_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PersonProto_PhoneNumber_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_PersonProto_PhoneNumber_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n*src/main/proto/com/fzc/domain/Person.p" +
      "roto\"\247\001\n\013PersonProto\022\021\n\tfirstName\030\001 \001(\t\022" +
      "\022\n\nsecondName\030\002 \001(\t\022\013\n\003age\030\003 \001(\005\022.\n\014phon" +
      "eNumbers\030\004 \003(\0132\030.PersonProto.PhoneNumber" +
      "\0324\n\013PhoneNumber\022\020\n\010areaCode\030\001 \001(\005\022\023\n\013pho" +
      "neNumber\030\002 \001(\003B*\n\024com.fzc.domain.protoB\020" +
      "OuterPersonProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_PersonProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PersonProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PersonProto_descriptor,
        new java.lang.String[] { "FirstName", "SecondName", "Age", "PhoneNumbers", });
    internal_static_PersonProto_PhoneNumber_descriptor =
      internal_static_PersonProto_descriptor.getNestedTypes().get(0);
    internal_static_PersonProto_PhoneNumber_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_PersonProto_PhoneNumber_descriptor,
        new java.lang.String[] { "AreaCode", "PhoneNumber", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
