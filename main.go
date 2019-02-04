package main

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"log"
	"os"
	"strings"
)

const permission = 0644

func main() {
	reader := bufio.NewReader(os.Stdin)
	fmt.Print("write entity name: ")
	entityName, _ := reader.ReadString('\n')
	fmt.Println(entityName)
	entityName = strings.Replace(entityName, "\n", "", -1)
	_ = os.Mkdir("./target", permission)

	createController(entityName)
	createService(entityName)
	createServiceImpl(entityName)
	createMapper(entityName)
	createRepository(entityName)
}

//Generate controller
func createController(entityName string) {
	sourcePath := "./source/controller/"
	targetPath := "./target/controller/"

	bytes, err := ioutil.ReadFile(sourcePath + "StudentController.java")
	if err != nil {
		log.Fatal("File can not be opened")
	}
	source := string(bytes)
	replaced := replaceAll(source, "student", entityName)
	_ = os.Mkdir(targetPath, permission)
	err = ioutil.WriteFile(targetPath+strings.Title(entityName)+"Controller.java", []byte(replaced), permission)
	if err != nil {
		log.Fatal("file can not be written")
	}
}

//Generate service
func createService(entityName string) {
	sourcePath := "./source/service/"
	targetPath := "./target/service/"

	bytes, err := ioutil.ReadFile(sourcePath + "StudentService.java")
	if err != nil {
		log.Fatal("File can not be opened")
	}
	source := string(bytes)
	replaced := replaceAll(source, "student", entityName)
	_ = os.Mkdir(targetPath, permission)
	err = ioutil.WriteFile(targetPath+strings.Title(entityName)+"Service.java", []byte(replaced), permission)
	if err != nil {
		log.Fatal("file can not be written")
	}
}

//Generate service impl
func createServiceImpl(entityName string) {
	sourcePath := "./source/service/impl/"
	targetPath := "./target/service/impl/"

	bytes, err := ioutil.ReadFile(sourcePath + "StudentServiceImpl.java")
	if err != nil {
		log.Fatal("File can not be opened")
	}
	source := string(bytes)
	replaced := replaceAll(source, "student", entityName)
	_ = os.Mkdir(targetPath, permission)
	err = ioutil.WriteFile(targetPath+strings.Title(entityName)+"ServiceImpl.java", []byte(replaced), permission)
	if err != nil {
		log.Fatal("file can not be written")
	}
}

//Generate mapper
func createMapper(entityName string) {
	sourcePath := "./source/mapper/"
	targetPath := "./target/mapper/"

	bytes, err := ioutil.ReadFile(sourcePath + "StudentMapper.java")
	if err != nil {
		log.Fatal("File can not be opened")
	}
	source := string(bytes)
	replaced := replaceAll(source, "student", entityName)
	_ = os.Mkdir(targetPath, permission)
	err = ioutil.WriteFile(targetPath+strings.Title(entityName)+"Mapper.java", []byte(replaced), permission)
	if err != nil {
		log.Fatal("file can not be written")
	}
}

//Generate repository
func createRepository(entityName string) {
	sourcePath := "./source/repository/"
	targetPath := "./target/repository/"

	bytes, err := ioutil.ReadFile(sourcePath + "StudentRepository.java")
	if err != nil {
		log.Fatal("File can not be opened")
	}
	source := string(bytes)
	replaced := replaceAll(source, "student", entityName)
	_ = os.Mkdir(targetPath, permission)
	err = ioutil.WriteFile(targetPath+strings.Title(entityName)+"Repository.java", []byte(replaced), permission)
	if err != nil {
		log.Fatal("file can not be written")
	}
}

func replaceAll(s string, from string, to string) string {
	uf := strings.Title(from)
	ut := strings.Title(to)
	replaced := strings.Replace(s, uf, ut, -1)
	return strings.Replace(replaced, from, to, -1)
}
