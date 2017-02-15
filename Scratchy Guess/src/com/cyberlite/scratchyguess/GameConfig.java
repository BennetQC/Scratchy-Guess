/**
 *	Copyright [2016] [Bennet Makwakwa]
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */
package com.cyberlite.scratchyguess;


import java.util.ArrayList;
import java.util.List;

/**
* Class holds Constants and references to assets/ drawable content
* @author  Bennet Makwakwa
* @version 1.0
* @since   2016 
*/
public class GameConfig
{

    public static final int NUMBER_OF_GAME_BUTTONS = 6;
    public static final int NUMBER_OF_HINTS = 4;
    public static final List names;
    public static final List pictures;

    public GameConfig()
    {
    }

    static 
    {
        names = new ArrayList();
        pictures = new ArrayList();
        String as[] = {
            "Michael Jackson", "Pharrell Williams", "Rihanna", "Ryan Gosling", "Angelina Jolie", "Bruce Willis", "Ben Affleck", "Miley Cyrus", "Leonardo DiCaprio", "Kristen Stewart", 
            "George Clooney", "Morgan Freeman", "Tom Cruise", "Brad Pitt", "Jennifer Aniston", "Emma Watson", "Rachel Weisz", "Jessica Alba", "Justin Bieber", "Natalie Portman", 
            "Kate Winslet", "Keira Knightley", "Charlize Theron", "Lady Gaga", "Jennifer Lawrence", "Sandra Bullock", "George Michael", "David Hasselhoff", "Shakira", "Lindsay Lohan", 
            "Paris Hilton"
        };
        String as1[] = {
            "drawable/pic_000.jpg", "drawable/pic_001.jpg", "drawable/pic_002.jpg", "drawable/pic_003.jpg", "drawable/pic_004.jpg", "drawable/pic_005.jpg", "drawable/pic_006.jpg", "drawable/pic_007.jpg", "drawable/pic_008.jpg", "drawable/pic_009.jpg", 
            "drawable/pic_010.jpg", "drawable/pic_011.jpg", "drawable/pic_012.jpg", "drawable/pic_013.jpg", "drawable/pic_014.jpg", "drawable/pic_015.jpg", "drawable/pic_016.jpg", "drawable/pic_017.jpg", "drawable/pic_018.jpg", "drawable/pic_019.jpg", 
            "drawable/pic_020.jpg", "drawable/pic_021.jpg", "drawable/pic_022.jpg", "drawable/pic_023.jpg", "drawable/pic_024.jpg", "drawable/pic_025.jpg"
        };
        names.add(as);
        pictures.add(as1);
        String as2[] = {
            "Audi", "Maserati", "Ford Mustang", "Skoda", "Mercedes", "Chrysler", "Dodge", "Ferrari", "Ford", "BMW", 
            "Maybach", "Rolls Royce", "Mini", "SsangYong", "Citroen", "Kia", "Infinity", "Volkswagen", "Jeep", "Mazda", 
            "Lamborghini", "Bentley", "Maserati", "Porshe", "Tesla", "Suzuki", "Smart", "Pontiac", "McLaren", "Bugatti"
        };
        String as3[] = {
            "drawable/pic_100.jpg", "drawable/pic_101.jpg", "drawable/pic_102.jpg", "drawable/pic_103.jpg", "drawable/pic_104.jpg", "drawable/pic_105.jpg", "drawable/pic_106.jpg", "drawable/pic_107.jpg", "drawable/pic_108.jpg", "drawable/pic_109.jpg", 
            "drawable/pic_110.jpg", "drawable/pic_111.jpg", "drawable/pic_112.jpg", "drawable/pic_113.jpg", "drawable/pic_114.jpg", "drawable/pic_115.jpg", "drawable/pic_116.jpg", "drawable/pic_117.jpg", "drawable/pic_118.jpg", "drawable/pic_119.jpg", 
            "drawable/pic_120.jpg", "drawable/pic_121.jpg", "drawable/pic_122.jpg", "drawable/pic_123.jpg", "drawable/pic_124.jpg"
        };
        names.add(as2);
        pictures.add(as3);
        String as4[] = {
            "Rome", "Melbourne", "Mecca", "Paris", "Dubai", "Cairo", "Los Angeles", "Istanbul", "Kuala Lumpur", "New York", 
            "Barcelona", "Amsterdam", "San Francisco", "Budapest", "Washington D.C.", "Sydney", "London", "Piza", "Seattle", "Venice", 
            "Mexico City", "Berlin", "Rio de Janeiro", "Bangkok", "Athens", "Oslo", "Dublin", "Luxembourg", "Brusselss", "Monaco"
        };
        String as5[] = {
            "drawable/pic_250.jpg", "drawable/pic_251.jpg", "drawable/pic_252.jpg", "drawable/pic_253.jpg", "drawable/pic_254.jpg", "drawable/pic_255.jpg", "drawable/pic_256.jpg", "drawable/pic_257.jpg", "drawable/pic_258.jpg", "drawable/pic_259.jpg", 
            "drawable/pic_260.jpg", "drawable/pic_261.jpg", "drawable/pic_262.jpg", "drawable/pic_263.jpg", "drawable/pic_264.jpg", "drawable/pic_265.jpg", "drawable/pic_266.jpg", "drawable/pic_267.jpg", "drawable/pic_268.jpg", "drawable/pic_269.jpg", 
            "drawable/pic_270.jpg", "drawable/pic_271.jpg", "drawable/pic_272.jpg", "drawable/pic_273.jpg", "drawable/pic_274.jpg"
        };
        names.add(as4);
        pictures.add(as5);
        String as6[] = {
            "Elephant", "Panda", "Rhino", "Wolf", "Lion", "Tiger", "Zebra", "Hipopotam", "Gorrila", "Chimpanzee", 
            "Lampart", "Giraffe", "Penguin", "Lemur", "Deer", "Coala", "Spider", "Crocodile", "Orca", "Sheep", 
            "Cow", "Horse", "Kangaroo", "Emu", "Parrot", "Dalmatian", "Albatross", "Coyote", "Fox", "Gopher"
        };
        String as7[] = {
            "drawable/pic_150.jpg", "drawable/pic_151.jpg", "drawable/pic_152.jpg", "drawable/pic_153.jpg", "drawable/pic_154.jpg", "drawable/pic_155.jpg", "drawable/pic_156.jpg", "drawable/pic_157.jpg", "drawable/pic_158.jpg", "drawable/pic_159.jpg", 
            "drawable/pic_160.jpg", "drawable/pic_161.jpg", "drawable/pic_162.jpg", "drawable/pic_163.jpg", "drawable/pic_164.jpg", "drawable/pic_165.jpg", "drawable/pic_166.jpg", "drawable/pic_167.jpg", "drawable/pic_168.jpg", "drawable/pic_169.jpg", 
            "drawable/pic_170.jpg", "drawable/pic_171.jpg", "drawable/pic_172.jpg", "drawable/pic_173.jpg", "drawable/pic_174.jpg"
        };
        names.add(as6);
        pictures.add(as7);
        String as8[] = {
            "Pluto", "Garfield", "Powerpuff Girls", "Scooby Doo", "Tweety", "Simpsons", "Hello Kitty", "Johnny Bravo", "Tom and Jerry", "Sponge Bob", 
            "My Little Pony", "Dexter", "Bob the Builder", "Pepa", "Spiderman", "Flinstones", "Smurfs", "Tom and Friends", "Winnie the Pooh", "Nemo", 
            "Curious George", "Clifford", "Elmo", "Ariel", "Tarzan", "Batman", "Woody Woodpecker", "Donald Duck", "Yogi Bear", "Pink Panther"
        };
        String as9[] = {
            "drawable/pic_050.jpg", "drawable/pic_051.jpg", "drawable/pic_052.jpg", "drawable/pic_053.jpg", "drawable/pic_054.jpg", "drawable/pic_055.jpg", "drawable/pic_056.jpg", "drawable/pic_057.jpg", "drawable/pic_058.jpg", "drawable/pic_059.jpg", 
            "drawable/pic_060.jpg", "drawable/pic_061.jpg", "drawable/pic_062.jpg", "drawable/pic_063.jpg", "drawable/pic_064.jpg", "drawable/pic_065.jpg", "drawable/pic_066.jpg", "drawable/pic_067.jpg", "drawable/pic_068.jpg", "drawable/pic_069.jpg", 
            "drawable/pic_070.jpg", "drawable/pic_071.jpg", "drawable/pic_072.jpg", "drawable/pic_073.jpg", "drawable/pic_074.jpg"
        };
        names.add(as8);
        pictures.add(as9);
        String as10[] = {
            "Twitter", "Mc Donalds", "Facebook", "Samsung", "KFC", "Apple", "LG", "Google", "Burger King", "Starbucks", 
            "SONY", "Sharp", "IBM", "Coca Cola", "GE", "Microsoft", "Disney", "Gillette", "Amazon", "Pepsi", 
            "H&M", "Nike", "IKEA", "Canon", "Adidas", "Asics", "Avon", "Abercrombie", "Olympus", "Osram", 
            "Yamaha"
        };
        String as11[] = {
            "drawable/pic_200.jpg", "drawable/pic_201.jpg", "drawable/pic_202.jpg", "drawable/pic_203.jpg", "drawable/pic_204.jpg", "drawable/pic_205.jpg", "drawable/pic_206.jpg", "drawable/pic_207.jpg", "drawable/pic_208.jpg", "drawable/pic_209.jpg", 
            "drawable/pic_210.jpg", "drawable/pic_211.jpg", "drawable/pic_212.jpg", "drawable/pic_213.jpg", "drawable/pic_214.jpg", "drawable/pic_215.jpg", "drawable/pic_216.jpg", "drawable/pic_217.jpg", "drawable/pic_218.jpg", "drawable/pic_219.jpg", 
            "drawable/pic_220.jpg", "drawable/pic_221.jpg", "drawable/pic_222.jpg", "drawable/pic_223.jpg", "drawable/pic_224.jpg"
        };
        names.add(as10);
        pictures.add(as11);
    }
}
