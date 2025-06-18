package com.banvenez.ast.dto;

import lombok.Data;

//import javax.persistence.ColumnResult;
//import javax.persistence.ConstructorResult;

//import javax.persistence.SqlResultSetMapping;

@Data

//@Entity
//@SqlResultSetMapping(
//        name = "mnuBDtoMapping",
//        classes = @ConstructorResult(
//                targetClass = mnuBDto.class,
//                columns = {
//                        @ColumnResult(name = "codmenu", type = Integer.class),
////                        @ColumnResult(name = "desmenu", type = String.class)
////                        @ColumnResult(name = "dirmenu", type = String.class),
//                        @ColumnResult(name = "menpadre", type = Integer.class)
////                        @ColumnResult(name = "codaplicativo", type = Integer.class),
//  //                      @ColumnResult(name = "ordmenu", type = Integer.class)
////                        @ColumnResult(name = "activoo", type = Integer.class)
//
//                }
//        )
//)


public class mnuBDto {

   private Integer codmenu;
 //  private String desmenu;
//    private String dirmenu;
    private Integer menpadre;
//    private Integer codaplicativo;
 //   private Integer ordmenu;
//   private Integer activoo;



//    public mnuBDto(Integer codmenu,Integer menpadre/*,Integer ordmenu, String desmenu, String dirmenu, Integer menpadre, Integer codaplicativo, Integer ordmenu,Integer activoo*/) {
//         this.codmenu = codmenu;
// //        this.desmenu = desmenu;
////        this.dirmenu = dirmenu;
//        this.menpadre = menpadre;
////        this.codaplicativo = codaplicativo;
////         this.ordmenu = ordmenu;
////        this.activoo = activoo;
//
//   }



}
