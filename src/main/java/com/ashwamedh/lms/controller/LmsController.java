package com.ashwamedh.lms.controller;

import com.ashwamedh.lms.model.Course;
import com.ashwamedh.lms.model.Question;
import com.ashwamedh.lms.repository.CourseRepository;
import com.ashwamedh.lms.repository.QuestionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LmsController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostConstruct
    public void init() {
        if (courseRepository.count() > 0) {
            return;
        }
        questionRepository.deleteAll();
        courseRepository.deleteAll();

        Course c1 = courseRepository.save(new Course(null, "Mahabharata Pariksha", "Test your knowledge of the great epic Mahabharata.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/Mahabharata_-The-Great-Dharma-Trial.png"));
        Course c2 = courseRepository.save(new Course(null, "Epic Ramayana Mahapariksha", "A comprehensive study and examination of the glorious Ramayana.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/Epic-Ramayana-Mahapariksha-Poster-1.png"));
        Course c3 = courseRepository.save(new Course(null, "Sanatan Vedic Knowledge Quiz", "Mix of Vedas, Upanishads, and Indian philosophy.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/ChatGPT-Image-Aug-25-2026-10_00_25-PM-1.png"));
        Course c4 = courseRepository.save(new Course(null, "Rigveda Mahapariksha", "Explore the ancient Rigveda, its Mandalas, and divine hymns.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/ChatGPT-Image-Aug-25-2026-09_47_19-PM-1.png"));
        Course c5 = courseRepository.save(new Course(null, "Jain Dharma Mahapariksha", "Learn the principles of Ahimsa, Anekantavada, and the Tirthankaras.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/ChatGPT-Image-Aug-25-2026-09_20_45-PM-1.png"));
        Course c6 = courseRepository.save(new Course(null, "Bauddha Dharma Mahapariksha", "Understand the Four Noble Truths and the teachings of Gautama Buddha.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/ChatGPT-Image-Aug-25-2026-09_31_25-PM-1-1.png"));

        // Mahabharata
        questionRepository.save(new Question(c1, "Who was the father of the Pandavas?", "Dhritarashtra", "Pandu", "Shantanu", "Bhishma", "B"));
        questionRepository.save(new Question(c1, "Who was the eldest of the Kauravas?", "Dushasana", "Vikarna", "Duryodhana", "Yuyutsu", "C"));
        questionRepository.save(new Question(c1, "Who was the teacher (Guru) of both the Pandavas and Kauravas in warfare?", "Kripacharya", "Dronacharya", "Parashurama", "Bhishma", "B"));
        questionRepository.save(new Question(c1, "Who was Arjuna's charioteer during the Kurukshetra war?", "Balarama", "Krishna", "Yudhishthira", "Satyaki", "B"));
        questionRepository.save(new Question(c1, "What was the name of Arjuna's famous bow?", "Sharanga", "Vijaya", "Gandiva", "Pinaka", "C"));

        // Ramayana
        questionRepository.save(new Question(c2, "Who was the father of Lord Rama?", "Janaka", "Dasharatha", "Vishwamitra", "Vashistha", "B"));
        questionRepository.save(new Question(c2, "Who was the wife of Lord Rama?", "Kaikeyi", "Mandodari", "Sita", "Urmila", "C"));
        questionRepository.save(new Question(c2, "Who abducted Sita?", "Kumbhakarna", "Ravana", "Vali", "Maricha", "B"));
        questionRepository.save(new Question(c2, "Who was Lord Rama's devoted companion who helped him find Sita?", "Hanuman", "Sugriva", "Jambavan", "Angada", "A"));
        questionRepository.save(new Question(c2, "For how many years was Lord Rama exiled from Ayodhya?", "10 years", "12 years", "14 years", "16 years", "C"));

        // Sanatan / Vedic
        questionRepository.save(new Question(c3, "How many Vedas are traditionally recognized in Hinduism?", "Two", "Three", "Four", "Five", "C"));
        questionRepository.save(new Question(c3, "Which Veda is particularly associated with melodies and chants?", "Rigveda", "Samaveda", "Yajurveda", "Atharvaveda", "B"));
        questionRepository.save(new Question(c3, "Who delivered the teachings of the Bhagavad Gita to Arjuna?", "Bhishma", "Dronacharya", "Lord Krishna", "Yudhishthira", "C"));
        questionRepository.save(new Question(c3, "The Upanishads are primarily concerned with which of the following?", "Warfare", "Commerce", "Spiritual knowledge", "Agriculture", "C"));
        questionRepository.save(new Question(c3, "Which of the following is traditionally regarded as the oldest of the four Vedas?", "Samaveda", "Yajurveda", "Atharvaveda", "Rigveda", "D"));

        // Rigveda
        questionRepository.save(new Question(c4, "How many Mandalas (books) are there in the Rigveda?", "8", "10", "12", "18", "B"));
        questionRepository.save(new Question(c4, "Which deity is praised in the largest number of hymns in the Rigveda?", "Agni", "Indra", "Varuna", "Surya", "B"));
        questionRepository.save(new Question(c4, "What is the first word of the Rigveda?", "Soma", "Indra", "Agni", "Om", "C"));
        questionRepository.save(new Question(c4, "The famous Gayatri Mantra is found in which Veda?", "Rigveda", "Samaveda", "Yajurveda", "Atharvaveda", "A"));
        questionRepository.save(new Question(c4, "The Purusha Sukta is found in which Mandala of the Rigveda?", "Mandala 1", "Mandala 5", "Mandala 10", "Mandala 12", "C"));

        // Jain Dharma
        questionRepository.save(new Question(c5, "Who is traditionally regarded as the first Tirthankara in Jainism?", "Mahavira", "Parshvanatha", "Rishabhanatha", "Neminatha", "C"));
        questionRepository.save(new Question(c5, "Who was the 24th and last Tirthankara of the current Jain tradition?", "Parshvanatha", "Mahavira", "Neminatha", "Ajitanatha", "B"));
        questionRepository.save(new Question(c5, "Which principle is most closely associated with the Jain teaching of non-violence?", "Aparigraha", "Ahimsa", "Asteya", "Brahmacharya", "B"));
        questionRepository.save(new Question(c5, "What concept emphasizes that reality can be understood from multiple perspectives?", "Karma", "Moksha", "Anekantavada", "Samvara", "C"));
        questionRepository.save(new Question(c5, "Where did Lord Mahavira attain Kevala Jnana (omniscience)?", "Under a Sal tree", "At Mount Abu", "At Shatrunjaya", "At Rajgir", "A"));

        // Bauddha Dharma
        questionRepository.save(new Question(c6, "Who is traditionally regarded as the founder of Buddhism?", "Mahavira", "Gautama Buddha", "Ashoka", "Nagarjuna", "B"));
        questionRepository.save(new Question(c6, "Where was Gautama Buddha born, according to Buddhist tradition?", "Bodh Gaya", "Sarnath", "Lumbini", "Kushinagar", "C"));
        questionRepository.save(new Question(c6, "Where did Gautama Buddha attain enlightenment?", "Lumbini", "Bodh Gaya", "Sarnath", "Kushinagar", "B"));
        questionRepository.save(new Question(c6, "What is the name of Buddha's first sermon?", "Mahaparinibbana", "Dhammacakkappavattana", "Metta", "Mangala", "B"));
        questionRepository.save(new Question(c6, "Which of the following is NOT one of the Four Noble Truths?", "Truth of suffering", "Origin of suffering", "Cessation of suffering", "Eternal happiness through wealth", "D"));
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Course> allCourses = courseRepository.findAll();
        
        List<Course> sliderCourses = allCourses.stream()
            .filter(c -> !c.getTitle().contains("Sanatan Vedic"))
            .toList();
            
        model.addAttribute("sliderCourses", sliderCourses);
        return "index";
    }

    @GetMapping("/course/{id}")
    public String courseDetails(@PathVariable Long id, Model model, jakarta.servlet.http.HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            session.setAttribute("redirectUrl", "/course/" + id);
            return "redirect:/login";
        }
        Course course = courseRepository.findById(id).orElse(null);
        model.addAttribute("course", course);
        return "course-details";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @Autowired
    private com.ashwamedh.lms.repository.EnrollmentRepository enrollmentRepository;

    @Autowired
    private com.ashwamedh.lms.repository.TestScoreRepository testScoreRepository;

    @GetMapping("/dashboard")
    public String dashboard(jakarta.servlet.http.HttpSession session, Model model) {
        com.ashwamedh.lms.model.User user = (com.ashwamedh.lms.model.User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        List<com.ashwamedh.lms.model.Enrollment> enrollments = enrollmentRepository.findByUser(user);
        List<com.ashwamedh.lms.model.TestScore> testScores = testScoreRepository.findByUserOrderByTakenAtDesc(user);
        
        model.addAttribute("user", user);
        model.addAttribute("enrollments", enrollments);
        model.addAttribute("testScores", testScores);
        return "dashboard";
    }

    @GetMapping("/enroll/{id}")
    public String enroll(@PathVariable Long id, jakarta.servlet.http.HttpSession session) {
        com.ashwamedh.lms.model.User user = (com.ashwamedh.lms.model.User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            // Check if already enrolled
            boolean alreadyEnrolled = enrollmentRepository.findByUser(user).stream()
                    .anyMatch(e -> e.getCourse().getId().equals(id));
            if (!alreadyEnrolled) {
                com.ashwamedh.lms.model.Enrollment e = new com.ashwamedh.lms.model.Enrollment(null, user, course, java.time.LocalDateTime.now());
                enrollmentRepository.save(e);
            }
        }
        return "redirect:/dashboard";
    }
}
