// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: drugAvailability.proto

package com.example.protos;

public interface FindDrugResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:protos.FindDrugResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .protos.Drug drugs = 1;</code>
   */
  java.util.List<com.example.protos.Drug> 
      getDrugsList();
  /**
   * <code>repeated .protos.Drug drugs = 1;</code>
   */
  com.example.protos.Drug getDrugs(int index);
  /**
   * <code>repeated .protos.Drug drugs = 1;</code>
   */
  int getDrugsCount();
  /**
   * <code>repeated .protos.Drug drugs = 1;</code>
   */
  java.util.List<? extends com.example.protos.DrugOrBuilder> 
      getDrugsOrBuilderList();
  /**
   * <code>repeated .protos.Drug drugs = 1;</code>
   */
  com.example.protos.DrugOrBuilder getDrugsOrBuilder(
      int index);
}