package br.com.kaiotavares.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


public class Utils {

    //Tudo que for nulo ele vai meslar com nosso objeto no banco de dados
    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }


    //Retorna array com todas as propiedades que são nulas
    public static String[] getNullPropertyNames(Object source){ 
        //BeanWrapper é uma interface do própio Java, que faz com que acessamos as propiedades de um objeto
        //BeanWrapperImpl é a implementação dessa interface
        final BeanWrapper src = new BeanWrapperImpl(source);
         //Gerar um array com todas as propiedades do objeto
         PropertyDescriptor[] pds = src.getPropertyDescriptors();
         //Criar um conjunto com as propiedades de valores nulos
         Set<String> emptyNames = new HashSet<>();
        
         for(PropertyDescriptor pd: pds){
            //Para cada propertyValue irei obtero o valor da propiedade atual
            Object srcValue = src.getPropertyValue(pd.getName());
            //Caso seja nula vou adicionar o nome da propiedade no conjunto
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
         }
         //Tamanho do array é o tamnaho do array de propiedades nulas
         String[] result = new String[emptyNames.size()];
         //Convertendo as propiedades nulas para o array de string
         return emptyNames.toArray(result);
    }
}
