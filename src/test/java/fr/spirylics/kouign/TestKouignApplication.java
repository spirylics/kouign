package fr.spirylics.kouign;

import org.springframework.boot.SpringApplication;

class TestKouignApplication {

	static void main(String[] args) {
		SpringApplication.from(KouignApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
